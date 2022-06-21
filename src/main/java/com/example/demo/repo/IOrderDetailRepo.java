package com.example.demo.repo;

import com.example.demo.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetailRepo extends JpaRepository<OrderDetail,Long> {
    List<OrderDetail> findByOrderId(long orderId);
}
