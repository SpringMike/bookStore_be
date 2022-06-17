package com.example.demo.security.service;

import com.example.demo.model.Account;
import com.example.demo.repo.IAccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IAccountRepo accountRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account =
                accountRepo.findByUserName(username)
                        .orElseThrow(() ->new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(account);

    }
}
