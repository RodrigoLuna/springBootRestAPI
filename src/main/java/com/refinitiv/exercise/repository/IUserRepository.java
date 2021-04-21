package com.refinitiv.exercise.repository;

import com.refinitiv.exercise.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long>{
    
}
