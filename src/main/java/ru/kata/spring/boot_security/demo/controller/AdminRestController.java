package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @ResponseBody
    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

   /* @GetMapping("/info")//// коментировать иначе json не отобразиться!
    public ResponseEntity<User> getCurrent(Principal principal) {
        return new ResponseEntity<>(userService.findUserByUsername(principal.getName()), HttpStatus.OK);
    }*/
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.findUserById(id),HttpStatus.OK);
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles(){
        return new ResponseEntity<>(roleService.findAllRoles(),HttpStatus.OK);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser, @PathVariable Long id){
        userService.updateUser(updatedUser,id);
        return ResponseEntity.ok(userService.findUserById(id));
    }
    @DeleteMapping("/users{id}")
public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.removeUser(id);
        return ResponseEntity.noContent().build();
}

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
       // user.setName(name);//как правильно назначить имя?
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}

