package il.co.orgo.orgo.controllers.V1;

import il.co.orgo.orgo.dto.SignRequestDto;
import il.co.orgo.orgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// Sign up controller, sign and add users to DB
@RestController
@RequestMapping(value = "api/v1/")
public class SignControllerV1 {

    private final UserService userService;

    @Autowired
    public SignControllerV1(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signup")
    public String sign(@RequestBody SignRequestDto signRequestDto) {

        userService.register(signRequestDto.fromDtoToUser(signRequestDto));
        return "User "+signRequestDto.getUsername()+" was successfully registered";
    }
}
