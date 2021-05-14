package il.co.orgo.orgo.mapper;

import il.co.orgo.orgo.dto.AdminUserDto;
import il.co.orgo.orgo.dto.UserDto;
import il.co.orgo.orgo.dto.UserSignupDto;
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


    public User toEntity(AdminUserDto dto) {
        return modelMapper.map(dto,User.class);
    }

    public User toEntity(UserSignupDto dto) {
        return modelMapper.map(dto,User.class);
    }

    @Override
    public User toEntity(UserDto dto) {
        return super.toEntity(dto);
    }

    @PostConstruct
    public void setupUserMapper(){
        modelMapper.createTypeMap(UserDto.class, User.class)
                .setPostConverter(toEntityConverter());

        modelMapper.createTypeMap(UserSignupDto.class, User.class)
                .setPostConverter(SignupEntityConverter());
    }

    @PostConstruct
    public void setupSignupMapper(){
        modelMapper.createTypeMap(UserSignupDto.class, User.class)
                .setPostConverter(SignupEntityConverter());
    }



    public Converter<UserSignupDto, User> SignupEntityConverter() {
        return mappingContext -> {
            User destination = mappingContext.getDestination();
            destination.setCreated(new Date());
            destination.setUpdated(new Date());
            destination.setAuthor_id(101L);
            destination.setUsername("Signup");
            return mappingContext.getDestination();
        };
    }

    @Override
    public Converter<UserDto, User> toEntityConverter(long autor, ) {
        return mappingContext -> {
            User destination = mappingContext.getDestination();
            destination.setCreated(new Date());
            destination.setUpdated(new Date());
            destination.setAuthor_id(202L);
            destination.setUsername("User");
            return mappingContext.getDestination();
        };
    }
}
