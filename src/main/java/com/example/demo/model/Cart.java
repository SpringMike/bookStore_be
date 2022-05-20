package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "accountId", insertable = false,updatable = false)
    private Account account;
    private Long accountId;


    @JsonIgnore
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private List<CartDetail> cartDetails;
}
