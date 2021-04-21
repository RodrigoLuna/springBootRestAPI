package com.refinitiv.exercise.repository;

import com.refinitiv.exercise.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Long>{
    
}
