package il.co.orgo.orgo.security.jwt;

import il.co.orgo.orgo.model.Role;
import il.co.orgo.orgo.model.Status;
import il.co.orgo.orgo.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    //Method to convert model.User to jwt.User
    public static JwtUser create(User user){
        return new JwtUser(user.getId(), user.getUsername(), user.getFirstName(),
                user.getLastName(), user.getPassword(), user.getEmail(), user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(), mapToGrantedAuthorities(new ArrayList<>(user.getRoles())));
    }

    //Method to convert Users roles to authorities
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles){
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
