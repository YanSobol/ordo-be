package il.co.orgo.orgo.mapper;

import il.co.orgo.orgo.dto.AdminUserDto;
import il.co.orgo.orgo.dto.BaseDto;
import il.co.orgo.orgo.dto.UserDto;
import il.co.orgo.orgo.model.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class UserMapper extends BaseMapper<User, UserDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        super(User.class, UserDto.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper(){
        modelMapper.createTypeMap(UserDto.class, User.class)
                .addMappings(m -> m.skip(User::setCreated))
                .addMappings(m -> m.skip(User::setUpdated))
                .setPostConverter(toEntityConverter());
    }

    @Override
    public Converter<UserDto, User> toEntityConverter() {
        return mappingContext -> {
            UserDto source = mappingContext.getSource();
            User destination = mappingContext.getDestination();
            mapSpecificFields(source, destination);
            return mappingContext.getDestination();
        };
    }

    @Override
    public void mapSpecificFields(UserDto source, User destination) {
        destination.setCreated(new Date());
        destination.setUpdated(new Date());
    }
}
