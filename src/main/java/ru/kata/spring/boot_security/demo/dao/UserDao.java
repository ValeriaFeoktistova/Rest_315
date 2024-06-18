package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void saveUser(User user);
    void updateUserForJs(User user);
    void updateUser(User user, Long id);

    void removeUser(Long id);

    User findUserById(Long id);

    User findUserByUsername(String username);

     User findUserByMail(String mail);

}

