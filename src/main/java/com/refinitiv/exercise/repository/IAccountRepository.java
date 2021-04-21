package com.refinitiv.exercise.repository;

import com.refinitiv.exercise.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long>{
    
}
