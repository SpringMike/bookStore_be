package com.example.demo.service.impl;

import com.example.demo.model.Transaction;
import com.example.demo.repo.ITransactionRepo;
import com.example.demo.service.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService implements ITransactionService {

    private final ITransactionRepo transactionRepo;

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepo.findAll();
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepo.save(transaction);
    }
}
