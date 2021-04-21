package com.refinitiv.exercise.service;

import java.util.List;
import java.util.Optional;

import com.refinitiv.exercise.model.User;
import com.refinitiv.exercise.repository.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User updateUser(User selectedUser) throws Exception {
        Optional<User> user = this.userRepository.findById(selectedUser.getId());
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setId(selectedUser.getId());
            updatedUser.setName(selectedUser.getName());
            updatedUser.setAccounts(selectedUser.getAccounts());
            return userRepository.save(updatedUser);
        } else {
            throw new Exception("Cannot update User");
        }
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User getUserById(long userId) throws Exception {
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception ("User " + userId  + " not found");
        }
    }

    public boolean deleteUser(long userId) {
        try {
            userRepository.deleteById(userId);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
