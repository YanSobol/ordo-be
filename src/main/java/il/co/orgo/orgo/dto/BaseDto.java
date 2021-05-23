package il.co.orgo.orgo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import il.co.orgo.orgo.model.Status;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseDto {

    private Long id;

    private Long author_id;

    private Date created;

    private Date updated;

    private Status status;
}
