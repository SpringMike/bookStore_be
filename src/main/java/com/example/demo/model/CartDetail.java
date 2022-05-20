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
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String createDate;
    private int quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bookId", insertable = false,updatable = false)
    private Book book;
    private Long bookId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cartId", insertable = false,updatable = false)
    private Cart cart;
    private Long cartId;
}
