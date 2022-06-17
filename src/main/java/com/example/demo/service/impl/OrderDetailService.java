package com.example.demo.service.impl;

import com.example.demo.model.Book;
import com.example.demo.model.OrderDetail;
import com.example.demo.repo.IBookRepo;
import com.example.demo.repo.IOrderDetailRepo;
import com.example.demo.repo.IOrderRepo;
import com.example.demo.service.IOrderDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderDetailService implements IOrderDetailService {

    private final IOrderDetailRepo orderDetailRepo;
    private final IBookRepo bookRepo;

    @Override
    public List<OrderDetail> getAll() {
        return null;
    }

    @Override
    public List<OrderDetail> save(List<OrderDetail> orderDetailList,long orderId) {
        for (OrderDetail orderDetail : orderDetailList) {
            orderDetail.setOrderId(orderId);
            Book book = bookRepo.findById(orderDetail.getBookId()).get();
            book.setQuantity(book.getQuantity() - orderDetail.getQuantity());
            bookRepo.save(book);
        }
        return orderDetailRepo.saveAll(orderDetailList);
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public Optional<OrderDetail> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(long id) {

    }
}
