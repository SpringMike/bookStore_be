package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Publisher;

import java.util.List;
import java.util.Optional;

public interface IPublisherService {

    List<Publisher> getAllPublisher();

    Category save(Publisher publisher);

    Category update(Publisher publisher);

    Optional<Publisher> findById(long id);

    void deleteById(long id);
}
