package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String adminPage(Model model, Principal principal) {
        model.addAttribute("user", new User());
        model.addAttribute("currentUser", userService.findByUsername(principal.getName()));
        model.addAttribute("roles", roleService.findAllRoles());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roleUser", roleService.findRoleById(13L).getName());
        model.addAttribute("roleAdmin", roleService.findRoleById(14L).getName());
        return "admin";
    }

    @GetMapping("/")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAllRoles());
        return "admin";
    }

    @PostMapping("/create")
    public String saveUser(Model model, @RequestParam("username") String name, @ModelAttribute("user") User user, @RequestParam("roleIds") List<Long> roleIds) {
        user.setName(name);
        user.setRoles(roleService.findRolesById(roleIds));
        //userService.createOreUpdateUser(user);
        userService.createUser(user);
        return "redirect:/admin";
    }

    @RequestMapping("/editUser")
    public String showUpdateForm(@RequestParam("userId") long id, Model model) {
        //User user = userService.getUser(id);
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.findAllRoles());
        return "new-user";
    }

    @PostMapping("/update")
    public String update(@RequestParam("userId") Long userId, @ModelAttribute("user") User user, @RequestParam("roleIds") List<Long> roleIds, @RequestParam("username") String name) {
        userService.getUser(userId).setName(name);
        List<Role> rolesAll = roleService.findRolesById(roleIds);
        userService.getUser(userId).setRoles(rolesAll);
        userService.updateUser(userService.getUser(userId));
        return "redirect:/admin";
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("userId") long id, @ModelAttribute(name = "user") User user, Model model) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}




