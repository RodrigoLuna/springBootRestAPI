package com.refinitiv.exercise.controller;

import java.util.List;

import com.refinitiv.exercise.model.Account;
import com.refinitiv.exercise.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/account")
    @ApiOperation(value = "Fetch All accounts from the database", notes = "Returns list of Accounts", response = Account.class)
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok().body(accountService.getAllAccounts());
    }
    
    @GetMapping("/account/{id}")
    @ApiOperation(value = "Fetch a specific account from the database", notes = "Returns the Account given it's Id", response = Account.class)
    public ResponseEntity<Object> getAccountById(@PathVariable long id) {
        Account account = accountService.getAccountById(id);
        if (account != null)
            return ResponseEntity.ok().body(account);
        else 
            return ResponseEntity.ok().body("Account id " + id + " not found");
    }

    @PostMapping("/account")
    @ApiOperation(value = "Create a new Account in the database", notes = "Returns the added Account if success", response = Account.class)
    public ResponseEntity<Account> createAccount(Account newAccount) {
        return ResponseEntity.ok().body(accountService.createAccount(newAccount));
    }

    @DeleteMapping("/account/{id}")
    @ApiOperation(value = "Delete an existing Account from the database", 
    notes = "If the provided account id matches any existing account in the database and the account is not binded to any User, it will be removed from the database",
    response = String.class)
    public String deleteAccount(@PathVariable long id) throws Exception {
        if (accountService.deleteAccount(id)) 
            return "Account " + id + " has been removed";
        else 
            return "Could not delete Account " +  id + ", it does not exists or it's still assiged to a user";
    }    
}
