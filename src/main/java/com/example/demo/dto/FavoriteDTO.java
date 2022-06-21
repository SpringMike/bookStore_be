package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@AllArgsConstructor
@Setter
@Getter
public class FavoriteDTO {
    @Id
    private Long id;
    private String backCoverImage;
    private String frontCoverImage;
    private String bookName;
    private Float price;
}
