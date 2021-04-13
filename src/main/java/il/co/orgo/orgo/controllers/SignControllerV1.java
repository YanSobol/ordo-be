package il.co.orgo.orgo.controllers;

import il.co.orgo.orgo.dto.AuthenticationRequestDto;
import il.co.orgo.orgo.model.User;
import il.co.orgo.orgo.service.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "api/v1/")
public class SignControllerV1 {

    private final UserService userService;

    public SignControllerV1(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("sign")
    public void sign(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            String password = requestDto.getPassword();
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            userService.register(newUser);

        } catch (AuthenticationException exception) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
