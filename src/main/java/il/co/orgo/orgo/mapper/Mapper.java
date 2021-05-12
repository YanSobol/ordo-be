package il.co.orgo.orgo.mapper;

import il.co.orgo.orgo.dto.BaseDto;
import il.co.orgo.orgo.model.BaseEntity;

public interface Mapper<E extends BaseEntity, D extends BaseDto>{
    E toEntity(D dto);
    D toDto(E entity);
}
