package il.co.orgo.orgo.repository;

import il.co.orgo.orgo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);


}
