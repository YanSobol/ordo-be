package il.co.orgo.orgo.dto;

import lombok.Data;

@Data
public class UserSignupDto extends BaseDto {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

}
