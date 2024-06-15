package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;

public interface RoleService {

    public List<Role> findAllRoles();

    public List<Role> findRolesById(List<Long> roleIds);

    public Role findRoleById(Long id);

}
