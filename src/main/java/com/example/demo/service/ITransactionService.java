package com.example.demo.service;

import com.example.demo.model.Transaction;

import java.util.List;

public interface ITransactionService {
    List<Transaction> getAllTransaction();

    Transaction save(Transaction transaction);
}
