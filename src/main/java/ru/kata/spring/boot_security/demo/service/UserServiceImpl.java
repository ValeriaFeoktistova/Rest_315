package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.dao.UserDaoImpl;
import ru.kata.spring.boot_security.demo.exeptions.UserCreationException;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    //private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDaoImpl userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
       // this.passwordEncoder = passwordEncoder;
    }
    @Override
    @Transactional
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    @Transactional
    public User findUserByMail(String mail) {
        return userDao.findUserByMail(mail);
    }

    @Override
    @Transactional
    public User findUserById(long id) {
        return userDao.findUserById(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }


 /*  @Override
      @Transactional
      public void saveUser(User user) {
          user.setPassword(passwordEncoder.encode(user.getPassword()));
          try {
              userDao.saveUser(user);
          } catch (PersistenceException e) {
              throw new UserCreationException("Failed to create user due to persistence error", e);
          }
      }*/
    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void removeUser(long id) {
        userDao.removeUser(id);
    }

    @Override
    @Transactional
    public void updateUserForJs(User user) {
        userDao.updateUserForJs(user);
    }

    @Override
    @Transactional
    public void updateUser(User user, Long id) {
        userDao.updateUser(user, id);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getRoles());
    }
}
