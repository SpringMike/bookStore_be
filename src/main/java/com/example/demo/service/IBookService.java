package com.example.demo.service;

import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<FeaturedBookDTO> getAllBookFeatured();

    Book save(Book book);

    Book update(Book book);

    Optional<Book> findById(long id);

    void deleteById(long id);
}
