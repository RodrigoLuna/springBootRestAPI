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

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    public User addAccountToUser(User user, Account account) {
        if (!user.getAccounts().contains(account)) {
            user.addAccount(account);
            userRepository.save(user);
        }
        return user;
    }

    public User removeAccountToUser(User user, Account account) {
        if (!user.getAccounts().contains(account)) {
            user.removeAccount(account);
            userRepository.save(user);
        }
        return user;
    }
}
