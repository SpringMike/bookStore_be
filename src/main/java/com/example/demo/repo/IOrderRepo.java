package com.example.demo.repo;

import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface IOrderRepo extends JpaRepository<Order,Long> {

    @Query(nativeQuery = true, value = "SELECT top 1 * FROM order_status_history inner join [order] o on o.id = order_status_history.order_id\n" +
            "where status_order_id = 1\n" +
            "ORDER BY order_status_history.create_date DESC")
    List<Order> findTopByOrderStatusHistoriesDesc(Long id);

    List<Order> findByAccountId(long id);
}
