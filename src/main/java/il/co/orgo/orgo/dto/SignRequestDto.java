package il.co.orgo.orgo.dto;

import il.co.orgo.orgo.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class SignRequestDto {

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public User fromDtoToUser(SignRequestDto signRequestDto){

        User newUser = new User();
        newUser.setUsername(signRequestDto.getUsername());
        newUser.setPassword(signRequestDto.getPassword());
        newUser.setFirstName(signRequestDto.getFirstName());
        newUser.setLastName(signRequestDto.getLastName());
        newUser.setEmail(signRequestDto.getEmail());
        newUser.setCreated(new Date());
        newUser.setUpdated(new Date());
        return newUser;



    }

}
