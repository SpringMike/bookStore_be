package com.example.demo.controller;

import com.example.demo.dto.TransactionDTO;
import com.example.demo.model.TransactionHistory;
import com.example.demo.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactionHistory")
@CrossOrigin("*")
public class TransactionHistoryController {
    private final ITransactionService transactionService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<TransactionDTO> getAllTransaction(){
        return transactionService.getAllTransaction();
    }
}
