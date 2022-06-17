package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String create_date;

    @Column(name = "is_done")
    private boolean isDone;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "orderId", insertable = false,updatable = false)
    private Order order;
    private Long orderId;


    @ManyToOne
    @JoinColumn(name = "statusOrderId", insertable = false,updatable = false)
    private StatusOrder statusOrder;
    private Long statusOrderId;
}
