package com.example.demo.controller;

import com.example.demo.model.Publisher;
import com.example.demo.model.Supplier;
import com.example.demo.service.impl.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping
    public List<Publisher> getAllSupplier(){
        return publisherService.getAllPublisher();
    }
}
