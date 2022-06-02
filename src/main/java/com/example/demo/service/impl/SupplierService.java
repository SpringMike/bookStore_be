package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.model.Supplier;
import com.example.demo.repo.ISupplierRepo;
import com.example.demo.service.ISupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class SupplierService implements ISupplierService {

    private final ISupplierRepo supplierRepo;

    @Override
    public List<Supplier> getAllSupplier() {
        return supplierRepo.findAll();
    }

    @Override
    public Category save(Supplier supplier) {
        return null;
    }

    @Override
    public Category update(Supplier supplier) {
        return null;
    }

    @Override
    public Optional<Supplier> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(long id) {

    }
}
