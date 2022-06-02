package com.example.demo.service;

import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.model.Author;
import com.example.demo.model.Book;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {
    List<Author> getAllAuthor();

    Author save(Author author);

    Author update(Author author);

    Optional<Author> findById(long id);

    void deleteById(long id);
}
