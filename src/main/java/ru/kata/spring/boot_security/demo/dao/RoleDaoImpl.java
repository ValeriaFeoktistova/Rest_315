package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public RoleDaoImpl() {
    }

    @Override
    public List<Role> findAllRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();

    }

    @Override
    public Role findRoleById(Long id) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.id = :id", Role.class);
        query.setParameter("id", id);
        Role role = query.getSingleResult();
        return role;
    }
}

