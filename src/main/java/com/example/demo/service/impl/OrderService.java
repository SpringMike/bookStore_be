package com.example.demo.service.impl;

import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.model.CartDetail;
import com.example.demo.model.ETypeOrder;
import com.example.demo.model.Order;
import com.example.demo.model.OrderStatusHistory;
import com.example.demo.repo.IBookRepo;
import com.example.demo.repo.IOrderRepo;
import com.example.demo.repo.IOrderStatusHistoryRepo;
import com.example.demo.service.IOrderHistoryStatusService;
import com.example.demo.service.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class OrderService implements IOrderService {
    private final IOrderRepo orderRepo;
    private final OrderHistoryStatusService orderHistoryStatusService;


    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Order> getAllOrder() {
        return orderRepo.findAll();
    }

    @Override
    public List<OrderDTO> findByStatusHistory(long id) {
        String sql ="SELECT\n" +
                "       order_status_history.id as id,\n" +
                "       o.id as orderId,\n" +
                "       a.full_name,\n" +
                "       a.phone_number,\n" +
                "       max_date = max(order_status_history.create_date),\n" +
                "       o.address,\n" +
                "       o.total,\n" +
                "       order_status_history.status_order_id\n" +
                "FROM order_status_history\n" +
                "         inner join [order] o on o.id = order_status_history.order_id\n" +
                "         inner join account a on a.id = o.account_id\n" +
                "         inner join order_detail od on o.id = od.order_id\n" +
                "group by  a.full_name, o.address,\n" +
                "          o.id, o.total, a.phone_number, order_status_history.status_order_id,order_status_history.is_done, order_status_history.id\n" +
                "having order_status_history.status_order_id = ?1 and order_status_history.is_done = 'true'";

        Query query = this.entityManager.createNativeQuery(sql.toString(),"FeaturedOrderMapping");
        query.setParameter(1,id);
        return query.getResultList();
    }

    @Override
    public Order save(Order order) {
        order.setTypeOrder(ETypeOrder.ONLINE);
        Order order1 = orderRepo.save(order);
        OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
        orderStatusHistory.setCreate_date(order.getCreate_date());
        orderStatusHistory.setOrderId(order.getId());
        orderStatusHistory.setStatusOrderId(1L);
        orderStatusHistory.setDone(true);
        orderHistoryStatusService.save(orderStatusHistory);
        return order1;
    }

    @Override
    public Order updateStatus(Order order, long orderStatusHistoryId) {
        Order orderFromDB = orderRepo.findById(order.getId()).orElse(null);
        OrderStatusHistory orderStatusHistoryFromDb = orderHistoryStatusService.findById(orderStatusHistoryId).get();
        if (orderFromDB != null) {
            orderFromDB.getOrderStatusHistories().add(order.getOrderStatusHistories().get(0));
            orderStatusHistoryFromDb.setDone(false);
            orderHistoryStatusService.save(orderStatusHistoryFromDb);
            orderRepo.save(orderFromDB);
        }
        return null;
    }

    @Override
    public List<Order> findByAccountId(long id) {
        return orderRepo.findByAccountId(id);
    }

    @Override
    public Optional<Order> findById(long id) {
        return orderRepo.findById(id);
    }

    @Override
    public void deleteById(long id) {

    }
}
