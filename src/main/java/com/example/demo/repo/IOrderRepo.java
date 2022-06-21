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

    @Query(nativeQuery = true,value = "select top 1 [order].id as id,[order].account_id,[order].total,\n" +
            "       [order].create_date,[order].type_order,[order].address,[order].description,[order].finished\n" +
            "from [order]\n" +
            "inner join account a on a.id = [order].account_id\n" +
            "where account_id=?1\n" +
            "order by id desc")
    Order findNewstOrderByAccountId(Long accountId);
}
