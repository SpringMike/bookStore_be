package com.example.demo.repo;

import com.example.demo.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepo extends JpaRepository<Supplier,Long> {
}
