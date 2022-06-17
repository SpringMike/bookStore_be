package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.service.ICartRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {
    private final ICartRepo cartRepo;

    public void save(long accountId){
        Cart cart = new Cart();
        cart.setAccountId(accountId);
        cart.setStatus(true);
        cartRepo.save(cart);
    }
}
