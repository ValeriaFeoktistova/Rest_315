package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(long id);

    User getUser(long id);

    User getMail(String mail);

    String findEmailByUsername(String username);

    public User findByUsername(String username);

    public User findByEmail(String mail);
}

