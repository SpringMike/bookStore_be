package com.example.demo.controller;

import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.model.Account;
import com.example.demo.service.impl.AccountService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public@RestController
@CrossOrigin("*")
@RequestMapping("api/accounts")
@AllArgsConstructor
class AccountController {

    private final AccountService accountService;

    @GetMapping
    public List<Account> getAllAccount() {
        return accountService.getAllAccount();
    }
    @PutMapping("/updateRole")
    public Account updateRole(@RequestBody Account account) {
        return accountService.updateRole(account);
    }
    @PutMapping("/updateStatus/{id}")
    public Account updateStatus(@PathVariable long id) {
        return accountService.updateStatus(id);
    }
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.save(account);
    }
}
