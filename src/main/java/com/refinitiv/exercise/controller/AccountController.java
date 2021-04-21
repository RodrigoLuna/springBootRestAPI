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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/account")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok().body(accountService.getAllAccounts());
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable long id) throws Exception {
        return ResponseEntity.ok().body(accountService.getAccountById(id));
    }

    @PostMapping("/account")
    public ResponseEntity<Account> createAccount(Account newAccount) {
        return ResponseEntity.ok().body(accountService.createAccount(newAccount));
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable long id, @RequestBody Account account) throws Exception {
        account.setId(id);
        return ResponseEntity.ok().body(accountService.updateAccount(account));
    }

    @DeleteMapping("/account/{id}")
    public String deleteAccount(@PathVariable long id) throws Exception {
        if (accountService.deleteAccount(id)) 
            return "Account " + id + " has been removed";
        else 
            return "Could not delete Account " +  id;
    }    
}
