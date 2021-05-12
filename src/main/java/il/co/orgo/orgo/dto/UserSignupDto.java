package il.co.orgo.orgo.dto;

import lombok.Data;

@Data
public class UserSignupDto {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

}
