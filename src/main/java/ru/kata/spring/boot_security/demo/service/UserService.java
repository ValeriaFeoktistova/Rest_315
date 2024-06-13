package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    void createOreUpdateUser(User user);

    User getUser(long id);

    User deleteUser(long id);

    public void createUser(User user);

    public void updateUser(User user);

    String findEmailByUsername(String username);

    public User findByUsername(String username);

    public UserDetails loadUserByUsername(String username);

}
