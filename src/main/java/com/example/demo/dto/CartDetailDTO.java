package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDTO {
    @Id
    private Long id;
    private Long idBook;
    private Long idCart;
    private String nameBook;
    private String fontImage;
    private String backImage;
    private Double price;
    private Integer quantity;
    private Double total;
    private Float newPrice;
    private Float newTotal;
    private Long promotionBlackListId;
    private Integer sale;
}
