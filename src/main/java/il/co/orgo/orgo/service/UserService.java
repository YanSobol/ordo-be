package il.co.orgo.orgo.service;

import il.co.orgo.orgo.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    User update(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

}
