package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
class UserController {

    private final UserService userService;
   // private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
      //  this.roleService = roleService;
    }

    @GetMapping("/info")
    public ResponseEntity<User> getCurrent(Principal principal) {
        return new ResponseEntity<>(userService.findUserByUsername(principal.getName()), HttpStatus.OK);
    }
}
