package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    User getUser(long id);

    void deleteUser(long id);

    void createUser(User user);

    void updateUser(User user);

    User findByUsername(String username);

    UserDetails loadUserByUsername(String username);

}
