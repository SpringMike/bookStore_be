package com.example.demo.service.impl;

import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.dto.TransactionDTO;
import com.example.demo.model.TransactionHistory;
import com.example.demo.repo.ITransactionRepo;
import com.example.demo.service.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Service
@AllArgsConstructor
public class TransactionService implements ITransactionService {
    private final ITransactionRepo transactionRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TransactionDTO> getAllTransaction() {
        List<TransactionDTO> listFeaturedTransaction = entityManager.createNamedQuery("getTransactionHistory").getResultList();
        return listFeaturedTransaction;
    }

    @Override
    public TransactionHistory save(TransactionHistory transaction) {
        return transactionRepo.save(transaction);
    }

    @Override
    public TransactionHistory findByOrderId(long orderId) {
        return transactionRepo.findByOrderId(orderId);
    }
}
