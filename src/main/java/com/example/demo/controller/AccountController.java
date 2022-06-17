package com.example.demo.controller;

import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.model.Account;
import com.example.demo.model.Cart;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.service.ICartRepo;
import com.example.demo.service.impl.AccountService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final ICartRepo cartRepo;
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Account> getAllAccount() {
        return accountService.getAllAccount();
    }

    @PutMapping("/updateRole")
    @PreAuthorize("hasRole('ADMIN')")
    public Account updateRole(@RequestBody Account account) {
        return accountService.updateRole(account);
    }


    @PutMapping("/updateStatus/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Account updateStatus(@PathVariable long id) {
        return accountService.updateStatus(id);
    }


    @GetMapping("/findCart/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Long findCartByAccountUd(@PathVariable Long id) {
       return cartRepo.findByAccountId(id).getId();
    }

}
