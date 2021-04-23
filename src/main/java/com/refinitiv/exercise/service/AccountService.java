package com.refinitiv.exercise.service;

import java.util.List;
import java.util.Optional;

import com.refinitiv.exercise.model.Account;
import com.refinitiv.exercise.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Creates a new Account
     * @param accountId
     * @return
     */
    public Account createAccount(Account newAccount) {
        return accountRepository.save(newAccount);
    }

    /**
     * Retreives all the existing Accounts
     * @return
     */
    public List<Account> getAllAccounts(){
        return this.accountRepository.findAll();
    }

    /**
     * Retreives an specific account given it's Id
     * @param id
     * @return The account if exists, otherwise returns null
     */    
    public Account getAccountById(long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()){
            return account.get();
        } else {
            return null;
        }
    }
    
    /**
     * Deletes an specific account given it's Id
     * @param accountId
     * @return True if the account could be deleted, false if there was an error
     */
    public boolean deleteAccount(long accountId) {
       try {
            accountRepository.deleteById(accountId);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
