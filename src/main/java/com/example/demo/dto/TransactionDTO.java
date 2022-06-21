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
public class TransactionDTO {
    @Id
    private Long id;

    private Float amount;
    private String bankCode;
    private String bankTranNo;
    private String cardType;
    private String orderInfo;
    private String createDate;
    private String accountName;
    private Long orderId;

}
