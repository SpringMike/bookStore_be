package com.example.demo.repo;

import com.example.demo.model.ERole;
import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IRoleRepo extends JpaRepository<Role,Long> {
    Role findByName(ERole name);

}