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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/user")
    @ApiOperation(value = "Fetch All Users from the database", notes = "Returns list of Users", response = User.class)
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "Fetch a specific user from the database", notes = "Returns the User given it's Id", response = User.class)
    public ResponseEntity<Object> getUserById(@PathVariable long id) {
        User user = userService.getUserById(id);
        if (user != null)
            return ResponseEntity.ok().body(user);
        else 
            return ResponseEntity.ok().body("User id " + id + " not found");
    }

    @PostMapping("/user")
    @ApiOperation(value = "Create a new User in the database", notes = "Returns the added User if success", response = Account.class)
    public ResponseEntity<User> createUser(User newUser) {
        return ResponseEntity.ok().body(userService.createUser(newUser));
    }
   
    @PostMapping("/user/{userId}") 
    @ApiOperation(value = "Assign an existing Account to an existing User", notes = "Verify that the selected Account and User exists in the database", response = Account.class)
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
    @ApiOperation(value = "Unbinds an Account from a User", notes = "Verify that the selected Account is currently binded to a User", response = Account.class)
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
