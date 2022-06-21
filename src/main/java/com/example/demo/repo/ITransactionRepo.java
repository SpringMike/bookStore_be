package com.example.demo.repo;

import com.example.demo.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepo extends JpaRepository<TransactionHistory,String> {
    TransactionHistory findByOrderId(long orderId);
}
