package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.Cart;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.service.IAccountService;
import com.example.demo.service.ICartRepo;
import com.example.demo.service.impl.AccountService;
import com.example.demo.service.impl.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAccountService accountService;
    private final CartService cartService;
    @PostMapping("/log-in")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(accountService.logIn(loginRequest));
    }

    @PostMapping("sign-up")
    public Account createAccount(@RequestBody Account account) {
        Account account1 =  accountService.save(account);
        cartService.save(account1.getId());
        return account1;
    }
}
