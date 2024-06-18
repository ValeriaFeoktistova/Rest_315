package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService{

    List<User> getAllUsers();

    void removeUser(long id);

    void saveUser(User user);
     void updateUserForJs(User user);
    void updateUser(User user, Long id);

    UserDetails loadUserByUsername(String username);

    User findUserByUsername(String username);

    User findUserByMail(String mail);

    User findUserById(long id);

}
