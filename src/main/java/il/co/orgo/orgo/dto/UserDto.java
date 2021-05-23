package il.co.orgo.orgo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import il.co.orgo.orgo.model.Role;
import il.co.orgo.orgo.model.User;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto extends BaseDto {

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
