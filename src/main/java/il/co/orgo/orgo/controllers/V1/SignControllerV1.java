package il.co.orgo.orgo.controllers.V1;

import il.co.orgo.orgo.dto.UserDto;
import il.co.orgo.orgo.mapper.UserMapper;
import il.co.orgo.orgo.model.User;
import il.co.orgo.orgo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;


@RestController
@RequestMapping(value = "api/v1/")
public class SignControllerV1 {

    private final ModelMapper modelMapper;
    private final UserMapper mapper;
    private final UserService userService;

    @Autowired
    public SignControllerV1(ModelMapper modelMapper, UserMapper mapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.mapper = mapper;
        this.userService = userService;
    }

    @PostMapping("signup")
    public UserDto sign(@RequestBody UserDto signRequestDto) {

        User user = modelMapper.map(signRequestDto, User.class);
        //User user = mapper.toEntity(signRequestDto);
        userService.register(user);
        return mapper.toDto(user);
    }
}
