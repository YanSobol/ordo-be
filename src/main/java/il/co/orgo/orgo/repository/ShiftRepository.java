package il.co.orgo.orgo.repository;

import il.co.orgo.orgo.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    Shift findByName(String name);
}
