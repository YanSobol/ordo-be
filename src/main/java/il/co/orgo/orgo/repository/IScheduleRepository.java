package il.co.orgo.orgo.repository;

import il.co.orgo.orgo.model.ScheduleShifts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IScheduleRepository extends JpaRepository<ScheduleShifts, Long> {

}
