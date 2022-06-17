package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Order;
import com.example.demo.model.OrderStatusHistory;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.response.JwtResponse;

import java.util.List;
import java.util.Optional;

public interface IOrderHistoryStatusService {

    List<OrderStatusHistory> getAllHistoryOrder();

    List<OrderStatusHistory> findByOrderId(long id);


    OrderStatusHistory save(OrderStatusHistory orderStatusHistory);

    OrderStatusHistory update(OrderStatusHistory orderStatusHistory);


    Optional<OrderStatusHistory> findById(long id);

    void deleteById(long id);

}
