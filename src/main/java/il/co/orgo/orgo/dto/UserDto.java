package il.co.orgo.orgo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import il.co.orgo.orgo.model.User;
import liquibase.pro.packaged.U;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;

    public User toUser(){
        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);

        return user;
    }

    public static UserDto fromUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstname(user.getFirstName());
        userDto.setLastname(user.getLastName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
