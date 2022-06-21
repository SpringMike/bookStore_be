package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Supplier;
import com.example.demo.service.impl.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Supplier> getAllSupplier(){
        return supplierService.getAllSupplier();
    }
}
