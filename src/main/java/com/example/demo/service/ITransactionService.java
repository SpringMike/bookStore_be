package com.example.demo.service;

import com.example.demo.dto.TransactionDTO;
import com.example.demo.model.TransactionHistory;

import java.util.List;
public interface ITransactionService {
    List<TransactionDTO> getAllTransaction();

    TransactionHistory save(TransactionHistory transaction);

    TransactionHistory findByOrderId(long orderId);
}
