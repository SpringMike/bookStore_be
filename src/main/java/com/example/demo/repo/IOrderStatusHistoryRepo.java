package com.example.demo.repo;

import com.example.demo.model.OrderStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface IOrderStatusHistoryRepo extends JpaRepository<OrderStatusHistory,Long> {
    @Query
    List<OrderStatusHistory> findByOrderId(long id);
}
