package com.example.demo.service.impl;

import com.example.demo.model.Order;
import com.example.demo.model.OrderStatusHistory;
import com.example.demo.repo.IOrderStatusHistoryRepo;
import com.example.demo.service.IOrderHistoryStatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderHistoryStatusService implements IOrderHistoryStatusService {
    private final IOrderStatusHistoryRepo orderStatusHistoryRepo;

    @Override
    public List<OrderStatusHistory> getAllHistoryOrder() {
        return null;
    }

    @Override
    public List<OrderStatusHistory> findByOrderId(long id) {
        return orderStatusHistoryRepo.findByOrderId(id);
    }



    @Override
    public OrderStatusHistory save(OrderStatusHistory orderStatusHistory) {
        return orderStatusHistoryRepo.save(orderStatusHistory);
    }

    @Override
    public OrderStatusHistory update(OrderStatusHistory orderStatusHistory) {
        return null;
    }

    @Override
    public Optional<OrderStatusHistory> findById(long id) {
        return orderStatusHistoryRepo.findById(id);
    }

    @Override
    public void deleteById(long id) {

    }
}
