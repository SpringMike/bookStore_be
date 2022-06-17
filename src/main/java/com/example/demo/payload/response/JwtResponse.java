package com.example.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;

    private String fullName;
    private String image;
    private String email;
    private String phoneNumber;
    private String mainAddress;
    private List<String> roles;


    public JwtResponse(String jwt, Long id, String username, String fullName, String email, String mainAddress, String image, String phoneNumber, String mainAddress1, List<String> roles) {
        this.token = jwt;
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.image = image;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.mainAddress = mainAddress;
        this.roles = roles;
    }
}
