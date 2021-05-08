package il.co.orgo.orgo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "shifts")
@Data
public class Shift extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "author_id")
    private Long author_id;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

}
