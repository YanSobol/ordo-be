package il.co.orgo.orgo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import il.co.orgo.orgo.model.Role;
import il.co.orgo.orgo.model.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private List<String> roles;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setFirstname(user.getFirstName());
        userDto.setLastname(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        return userDto;
    }

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);

        return user;
    }
}
