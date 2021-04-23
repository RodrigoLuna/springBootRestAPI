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

    public Account createAccount(Account newAccount) {
        return accountRepository.save(newAccount);
    }

    public Account updateAccount(Account selectedAccount) throws Exception {
        Optional<Account> account = this.accountRepository.findById(selectedAccount.getId());
        if (account.isPresent()) {
            Account updatedAccount = account.get();
            updatedAccount.setId(selectedAccount.getId());
            updatedAccount.setName(selectedAccount.getName());
            updatedAccount.setCurrency(selectedAccount.getCurrency());
            return accountRepository.save(updatedAccount);
        } else {
            throw new Exception("Cannot update Account");
        }
    }

    public List<Account> getAllAccounts(){
        return this.accountRepository.findAll();
    }

    public Account getAccountById(long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()){
            return account.get();
        } else {
            return null;
        }
    }

    public boolean deleteAccount(long accountId) {
       try {
            accountRepository.deleteById(accountId);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
