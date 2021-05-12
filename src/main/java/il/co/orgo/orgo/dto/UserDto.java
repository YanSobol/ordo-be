package il.co.orgo.orgo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import il.co.orgo.orgo.model.User;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto extends BaseDto {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;
}
