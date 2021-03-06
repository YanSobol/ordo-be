package il.co.orgo.orgo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity {
    @Column(name = "name")
    private String name;


    // mappedBy = "roles": description of mapping and connection between User and Role in class User->roles
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;


    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }
}
