package il.co.orgo.orgo.service.implementation;

import il.co.orgo.orgo.model.Role;
import il.co.orgo.orgo.model.Status;
import il.co.orgo.orgo.model.User;
import il.co.orgo.orgo.repository.RoleRepository;
import il.co.orgo.orgo.repository.ShiftRepository;
import il.co.orgo.orgo.repository.UserRepository;
import il.co.orgo.orgo.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ShiftRepository shiftRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, RoleRepository roleRepository,
                                     ShiftRepository shiftRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.shiftRepository = shiftRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Date date = new Date();
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setCreated(date);
        user.setUpdated(date);
        user.setStatus(Status.ACTIVE);
        //TODO Add author_id add logic
        user.setAuthor_id(101L);


        User registeredUser = userRepository.save(user);
        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @SneakyThrows
    @Override
    public User update(User user) {
        Date date = new Date();
        User existUser = userRepository.findByUsername(user.getUsername());

        List<Field> fields = getFields(user);
        for (Field field : fields) {

            System.out.println(field.getName());
            field.setAccessible(true);
            Object fieldValue = field.get(user);
            if(fieldValue != null){
                field.set(existUser,fieldValue);
            }
        }
        existUser.setUpdated(date);
        userRepository.save(existUser);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = userRepository.findAll();
        log.info("IN getAll - {} users found", allUsers);
        return allUsers;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {}, found by username: {}", user.getId(), username);
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn("IN findByUsername - no user found by id: {}", id);
            return null;
        }
        log.info("IN findByUsername - user: {}, found by id: {}", user, id);
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {}, successfully deleted", id);
    }

    private <T> List<Field> getFields(T t) {
        List<Field> fields = new ArrayList<>();
        Class<?> clazz = t.getClass();
        while (clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }
}
