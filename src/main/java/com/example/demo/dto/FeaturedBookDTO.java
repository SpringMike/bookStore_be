package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeaturedBookDTO {
    @Id
    private Long id;
    private String back_cover_image;
    private String front_cover_image;
    private String description;
    private String language;
    private String name;
    private int number_page;
    private String public_year;
    private int quantity;
    private boolean status;
    private String authorName;
    private String categoryName;
    private String supplierName;
    private String publisherName;
    private Float price;
    private Integer sale;
    private Float newPrice;
    private Integer blackListPromotionId;

}
