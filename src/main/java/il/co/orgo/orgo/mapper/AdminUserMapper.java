package il.co.orgo.orgo.mapper;

import il.co.orgo.orgo.dto.AdminUserDto;
import il.co.orgo.orgo.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminUserMapper extends BaseMapper<User, AdminUserDto>{

    private final ModelMapper modelMapper;

    @Autowired
    public AdminUserMapper(ModelMapper modelMapper) {
        super(User.class, AdminUserDto.class);
        this.modelMapper = modelMapper;
    }
}
