package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepo extends JpaRepository<Cart,Long> {
    Cart findByAccountId(Long accountId);
}
