package com.refinitiv.exercise.service;

import java.util.List;
import java.util.Optional;

import com.refinitiv.exercise.model.Account;
import com.refinitiv.exercise.model.User;
import com.refinitiv.exercise.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new User
     * @param newUser
     * @return
     */
    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    /**
     * Retreives all existing Users
     * @return
     */
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    /**
     * Retreives an specific user given it's Id
     * @param userId
     * @return The user if exists, otherwise returns null
     */    
    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    /**
     * Binds an Account and a User by the given Id's
     * @param user
     * @param account
     * @return
     */
    public User addAccountToUser(User user, Account account) {
        if (!user.getAccounts().contains(account)) {
            user.addAccount(account);
            userRepository.save(user);
        }
        return user;
    }

     /**
     * Unbinds an Account from a User given the Id's
     * @param user
     * @param account
     * @return
     */
    public User removeAccountToUser(User user, Account account) {
        if (user.getAccounts().contains(account)) {
            user.removeAccount(account);
            userRepository.save(user);
        }
        return user;
    }
}
