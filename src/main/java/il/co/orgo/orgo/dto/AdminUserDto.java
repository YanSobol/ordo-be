package il.co.orgo.orgo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import il.co.orgo.orgo.model.Status;
import il.co.orgo.orgo.model.User;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto extends BaseDto {

    private Long id;

    private String username;

    private String firstname;

    private String lastname;

    private String email;

    private Date created;

    private Status status;


}
