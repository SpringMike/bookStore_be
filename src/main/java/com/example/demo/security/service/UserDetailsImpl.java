package com.example.demo.security.service;

import com.example.demo.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;
    private String fullName;
    private String image;
    private String email;
    private String phoneNumber;
    private String mainAddress;


    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;


    public UserDetailsImpl(Long id, String userName, String password, String fullName,String image, String email, String mainAddress, String phoneNumber, List<GrantedAuthority> authorities) {
        this.id = id;
        this.username = userName;
        this.fullName = fullName;
        this.image = image;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.mainAddress = mainAddress;
        this.password = password;
        this.authorities = authorities;
    }


    public static UserDetailsImpl build(Account account) {
        List<GrantedAuthority> authorities = account.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                account.getId(),
                account.getUserName(),
                account.getPassword(),
                account.getFullName(),
                account.getImage(),
                account.getEmail(),
                account.getMainAddress(),
                account.getPhoneNumber(),
                authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
