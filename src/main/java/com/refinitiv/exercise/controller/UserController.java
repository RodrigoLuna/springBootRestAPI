package com.refinitiv.exercise.controller;

import java.util.List;

import com.refinitiv.exercise.model.User;
import com.refinitiv.exercise.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) throws Exception {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(User newUser) {
        return ResponseEntity.ok().body(userService.createUser(newUser));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) throws Exception {
        user.setId(id);
        return ResponseEntity.ok().body(userService.updateUser(user));
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable long id) throws Exception {
        if (userService.deleteUser(id))
            return "Usuer Id " + id + " has been deleted";
        else
        return "Could not delete user " + id;
    }
}
