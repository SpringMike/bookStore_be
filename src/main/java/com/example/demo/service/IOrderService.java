package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {

    List<Order> getAllOrder();

    Order findNewstOrderByAccount(Long accountId);

    List<OrderDTO> findByStatusHistory(long id);

    Order save(Order order);

    Order updateStatus(Order order,long orderStatusHistoryId);

    List<Order> findByAccountId(long id);

    Order updateIsFinished(Order order);

//    Order updateStatus(long id);

    Optional<Order> findById(long id);

    void deleteById(long id);
}
