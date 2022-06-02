package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface ISupplierService {
    List<Supplier> getAllSupplier();

    Category save(Supplier supplier);

    Category update(Supplier supplier);

    Optional<Supplier> findById(long id);

    void deleteById(long id);
}
