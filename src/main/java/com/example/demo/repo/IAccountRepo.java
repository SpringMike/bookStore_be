package com.example.demo.repo;

import com.example.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepo extends JpaRepository<Account,Long> {
    Optional<Account> findByUserName(String username);
    Boolean existsByUserName(String username);
}
