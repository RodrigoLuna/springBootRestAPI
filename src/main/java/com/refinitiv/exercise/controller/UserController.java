package com.refinitiv.exercise.controller;

import java.util.List;

import com.refinitiv.exercise.model.Account;
import com.refinitiv.exercise.model.User;
import com.refinitiv.exercise.service.AccountService;
import com.refinitiv.exercise.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable long id) {
        User user = userService.getUserById(id);
        if (user != null)
            return ResponseEntity.ok().body(user);
        else 
            return ResponseEntity.ok().body("User id " + id + " not found");
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(User newUser) {
        return ResponseEntity.ok().body(userService.createUser(newUser));
    }
   
    @PostMapping("/user/{userId}") 
    public ResponseEntity<Object> addAccountToUser(
        @PathVariable long userId, 
        @RequestParam("account_id")long accountId) {

        User user = userService.getUserById(userId);
        if (user != null) {
            Account account = accountService.getAccountById(accountId);
            if (account != null)
                return ResponseEntity.ok().body(userService.addAccountToUser(user, account));
            else
                return ResponseEntity.ok().body("Cannot find Account Id " + accountId + " to bind it to User " + user.getName());

        } else {
            return ResponseEntity.ok().body("The User with id " + userId + " does not exist");
        }
    }
    

    @DeleteMapping("/user/{userId}") 
    public ResponseEntity<Object> removeAccountFromUser(
        @PathVariable long userId, 
        @RequestParam("account_id")long accountId) {

        User user = userService.getUserById(userId);
        if (user != null) {
            Account account = accountService.getAccountById(accountId);
            if (account != null)
                return ResponseEntity.ok().body(userService.removeAccountToUser(user, account));
            else
                return ResponseEntity.ok().body("Could not find Account " + accountId + " to bind it to User " + userId);

        } else {
            return ResponseEntity.ok().body("Could not find User " + userId);
        }
    }
}
