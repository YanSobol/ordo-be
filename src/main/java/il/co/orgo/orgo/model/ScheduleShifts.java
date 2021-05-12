package il.co.orgo.orgo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "schedule")
@Data
public class ScheduleShifts extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "shift_id")
    private Long shiftId;

}
