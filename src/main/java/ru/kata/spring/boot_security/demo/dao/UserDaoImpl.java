package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.exeptions.UserNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    public UserDaoImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
        //entityManager.flush(); было 15 июня
    }
    @Override
    public void removeUser(Long id) {
        entityManager.remove(findUserById(id));
    }

    @Override
    public void updateUserForJs(User user) {
        entityManager.merge(user);
        //entityManager.flush();
    }

    @Override
    public void updateUser(User user, Long id) {
        entityManager.merge(user);
        //entityManager.flush();
    }
   /* @Override
    @Transactional
    public void updateUser(User user, Long id) {
        User userFromDB = findUserById(id).get();
        userFromDB.setName(user.getName());
        userFromDB.setLastName(user.getLastName());
        userFromDB.setPassword(passwordEncoder.encode(user.getPassword()));
        userFromDB.setEmail(user.getEmail());
        userFromDB.setRoles(user.getRoles());
        saveUser(userFromDB);
    }*/

    @Override//getUser
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    // public User findUserById(long id) {return entityManager.find(User.class, id);}
    @Override
    public User findUserByUsername(String username) throws UserNotFoundException {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.name = :username", User.class);
        query.setParameter("username", username);

        User result = query.getSingleResult();
        if (result == null) {
            throw new UserNotFoundException("User not found");
        }

        return result;
    }
    @Override
    public User findUserByMail(String mail) throws UserNotFoundException {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.mail = :mail", User.class); // новый метод 16 июня
        query.setParameter("mail", mail);

        User result = query.getSingleResult();
        if (result == null) {
            throw new UserNotFoundException("User not found");
        }

        return result;
    }



    /* @Override
     public void deleteUser(long id) {
         if (entityManager.find(User.class, id) == null) {
             throw new NullPointerException("User not found");
         }
         entityManager.createQuery("DELETE FROM User u WHERE u.id = :id")
                 .setParameter("id", id)
                 .executeUpdate();
     }*/


}
