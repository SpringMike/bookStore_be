package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;
    private String fullName;
    private String image;
    private String email;
    private String phoneNumber;
    private boolean status;
    private String mainAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private List<Address> addresses;


    @JsonIgnore
    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private List<Cart> carts;

    @JsonIgnore
    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private List<Favorite> favorites;
}
