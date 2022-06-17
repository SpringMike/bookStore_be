package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    @Id
    private Long id;
    private long orderId;
    private String fullName;
    private String phoneNumber;
    private String createDate;
    private String address;
    private double total;
    private long statusOrderId;
}
