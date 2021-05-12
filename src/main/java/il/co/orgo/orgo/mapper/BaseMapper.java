package il.co.orgo.orgo.mapper;

import il.co.orgo.orgo.dto.BaseDto;
import il.co.orgo.orgo.model.BaseEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class BaseMapper<E extends BaseEntity, D extends BaseDto> implements Mapper<E, D> {

    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    @Autowired
    ModelMapper modelMapper;


    public BaseMapper(Class<E> entityClass, Class<D> dtoClass){
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto)
                ? null
                : modelMapper.map(dto, entityClass);
    }

    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity)
                ? null
                : modelMapper.map(entity, dtoClass);
    }

    public Converter<E, D> toDtoConverter(){
        return mappingContext -> {
            E source = mappingContext.getSource();
            D destination = mappingContext.getDestination();
            mapSpecificFields(source, destination);
            return mappingContext.getDestination();
        };
    }

    public Converter<D, E> toEntityConverter(){
        return mappingContext -> {
            D source = mappingContext.getSource();
            E destination = mappingContext.getDestination();
            mapSpecificFields(source, destination);
            return mappingContext.getDestination();
        };
    }

    public void mapSpecificFields(E source, D destination) {
    }

    public void mapSpecificFields(D source, E destination) {
    }

}
