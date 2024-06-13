package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public List<Role> findAllRoles() {
        return roleDao.findAllRoles();
    }

    @Override
    @Transactional
    public List<Role> findRolesById(List<Long> roleIds) {
        List<Role> roles = new ArrayList<>();
        for (Long roleId : roleIds) {
            Role role = roleDao.findRoleById(roleId);
            if (role != null) {
                roles.add(role);
            }
        }
        return roles;
    }

    @Override
    @Transactional
    public Role findRoleById(Long id) {
        return roleDao.findRoleById(id);
    }
}
