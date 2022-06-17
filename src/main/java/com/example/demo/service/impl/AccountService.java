package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.repo.IAccountRepo;
import com.example.demo.repo.IRoleRepo;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.service.UserDetailsImpl;
import com.example.demo.service.IAccountService;
import com.example.demo.service.ICartRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    public final IAccountRepo accountRepo;
    public final IRoleRepo roleRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    @Override
    public List<Account> getAllAccount() {
        return accountRepo.findAll();
    }

    @Override
    public Account save(Account account) {
        account.setStatus(true);
        Role userRole = roleRepo.findByName(ERole.ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        account.setRoles(roles);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepo.save(account);
    }

    @Override
    public Account updateRole(Account account) {
        Account accountFromDb = accountRepo.findById(account.getId()).orElse(null);
        if (accountFromDb!= null){
            Set<Role> roles = new HashSet<>();
            Set<Role> strRoles = account.getRoles();
            strRoles.forEach(role -> {
                switch (String.valueOf(role.getName())) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN);
                        roles.add(adminRole);
                        break;
                    case "ROLE_USER":
                        Role userRole = roleRepo.findByName(ERole.ROLE_USER);
                        roles.add(userRole);
                        break;
                    case "ROLE_SUPPER_ADMIN":
                        Role superAdminRole = roleRepo.findByName(ERole.ROLE_SUPPER_ADMIN);
                        roles.add(superAdminRole);
                        break;
                }
            });
            accountFromDb.setRoles(roles);
            accountRepo.save(accountFromDb);
        }
        return null;
    }
    @Override
    public Account updateStatus(Long id) {
        Account accountFromDb = accountRepo.findById(id).orElse(null);
        if (accountFromDb!= null){
            if (accountFromDb.isStatus()){
                accountFromDb.setStatus(false);
                accountRepo.save(accountFromDb);
            }else {
                accountFromDb.setStatus(true);
                accountRepo.save(accountFromDb);
            }
        }
        return null;
    }
    @Override
    public Optional<Account> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public JwtResponse logIn(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getFullName(),
                userDetails.getEmail(),
                userDetails.getMainAddress(),
                userDetails.getImage(),
                userDetails.getPhoneNumber(),
                userDetails.getMainAddress(),
                roles);
    }


}
