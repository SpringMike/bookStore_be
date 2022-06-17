package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface IOrderDetailService {
    List<OrderDetail> getAll();

    List<OrderDetail> save(List<OrderDetail> orderDetailList,long orderId);

    OrderDetail update(OrderDetail orderDetail);

//    Order updateStatus(long id);

    Optional<OrderDetail> findById(long id);

    void deleteById(long id);
}
