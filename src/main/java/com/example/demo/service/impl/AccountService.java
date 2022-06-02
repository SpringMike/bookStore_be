package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repo.IAccountRepo;
import com.example.demo.repo.IRoleRepo;
import com.example.demo.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    public final IAccountRepo accountRepo;
    public final IRoleRepo roleRepo;

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


}
