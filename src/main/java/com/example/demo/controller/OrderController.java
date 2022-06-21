package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
import com.example.demo.model.*;
import com.example.demo.service.impl.OrderDetailService;
import com.example.demo.service.impl.OrderHistoryStatusService;
import com.example.demo.service.impl.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    private final OrderHistoryStatusService orderHistoryStatusService;
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Order> getAllOrder() {
        return orderService.getAllOrder();
    }

    @GetMapping("/findByStatus/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderDTO> getAllOrderByStatus(@PathVariable long id) {
        return orderService.findByStatusHistory(id);
    }
    @GetMapping("/findById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Order> getAllOrderById(@PathVariable long id) {
        return orderService.findById(id);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Order save(@RequestBody Order order) {
        Order order1 = orderService.save(order);
        orderDetailService.save(order.getOrderDetails(), order1.getId());
        return order1;
    }

    @PutMapping("/updateStatus/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Order updateStatus(@RequestBody Order order, @PathVariable long id) {
        return orderService.updateStatus(order,id);
    }

    @GetMapping("findByHistoryStatus/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderStatusHistory> findHistoryStatus(@PathVariable long id) {
        return orderHistoryStatusService.findByOrderId(id);
    }
    @GetMapping("findByAccount/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Order> findByAccountId(@PathVariable long id) {
        return orderService.findByAccountId(id);
    }
    @PutMapping("/updateFinished")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Order updateFinished(@RequestBody Order order) {
        return orderService.updateIsFinished(order);
    }
}
