package il.co.orgo.orgo.controllers.V1;

import il.co.orgo.orgo.dto.AuthenticationRequestDto;
import il.co.orgo.orgo.dto.AuthenticationResponseDto;
import il.co.orgo.orgo.model.User;
import il.co.orgo.orgo.security.jwt.JwtTokenProvider;
import il.co.orgo.orgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


// Login controller: login users and getting jwtToken
@RestController
@RequestMapping(value = "api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto){
        try {
            String username = requestDto.getUsername();
            String password = requestDto.getPassword();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(token);
            User user = userService.findByUsername(username);

            if (user == null) throw new UsernameNotFoundException("User with username: " +username+ ", not found");

            //Response creation using AuthenticationResponseDto
            AuthenticationResponseDto responseDto = new AuthenticationResponseDto(username,jwtTokenProvider.createToken(user));
            return ResponseEntity.ok(responseDto);

        }catch (AuthenticationException exception){
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
