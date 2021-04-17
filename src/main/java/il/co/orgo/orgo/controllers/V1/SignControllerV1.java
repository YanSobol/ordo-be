package il.co.orgo.orgo.controllers.V1;
import il.co.orgo.orgo.dto.SignRequestDto;
import il.co.orgo.orgo.service.UserService;
import org.springframework.web.bind.annotation.*;



// Sign up controller, sign and add users to DB
@RestController
@RequestMapping(value = "api/v1/")
public class SignControllerV1 {

    private final UserService userService;

    public SignControllerV1(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signup")
    public String sign(@RequestBody SignRequestDto signRequestDto) {

        userService.register(signRequestDto.fromDtoToUser(signRequestDto));
        return "api/v1/auth/login";
    }
}
