package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.model.Publisher;
import com.example.demo.repo.IPublisherRepo;
import com.example.demo.service.IPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherService implements IPublisherService {

    private final IPublisherRepo publisherRepo;

    @Override
    public List<Publisher> getAllPublisher() {
        return publisherRepo.findAll();
    }

    @Override
    public Category save(Publisher publisher) {
        return null;
    }

    @Override
    public Category update(Publisher publisher) {
        return null;
    }

    @Override
    public Optional<Publisher> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(long id) {

    }
}
