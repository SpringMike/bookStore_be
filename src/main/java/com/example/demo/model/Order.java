package com.example.demo.model;

import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="`order`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SqlResultSetMapping(
        name = "FeaturedOrderMapping",
        classes = {
                @ConstructorResult(
                        targetClass = OrderDTO.class,
                        columns = {
                                @ColumnResult(name="id", type = Long.class),
                                @ColumnResult(name="orderId", type = Long.class),
                                @ColumnResult(name="full_name", type = String.class),
                                @ColumnResult(name="phone_number", type = String.class),
                                @ColumnResult(name="max_date", type = String.class),
                                @ColumnResult(name="address", type = String.class),
                                @ColumnResult(name="total", type = Double.class),
                                @ColumnResult(name="status_order_id", type = Long.class),
                        }
                )
        }
)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String create_date;
    private float total;
    private String description;

    @Enumerated(EnumType.STRING)
    private ETypeOrder typeOrder;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "accountId", insertable = false,updatable = false)
    private Account account;
    private Long accountId;


    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderStatusHistory> orderStatusHistories;


    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @OneToOne(mappedBy = "order")
    private Transaction transaction;
}
