package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Author;

import java.util.List;
import java.util.Optional;

public interface IAccountService {

    List<Account> getAllAccount();

    Account save(Account account);

    Account updateRole(Account account);

    Account updateStatus(Long id);

    Optional<Account> findById(long id);

    void deleteById(long id);
}
