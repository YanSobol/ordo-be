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

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    // mappedBy = "shifts": description of mapping and connection between User and Shift in class User->roles
    @ManyToMany(mappedBy = "shifts", fetch = FetchType.LAZY)
    private List<User> users;
}
