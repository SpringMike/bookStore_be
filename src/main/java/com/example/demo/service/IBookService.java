package com.example.demo.service;

import com.example.demo.dto.BookResponse;
import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<FeaturedBookDTO> getAllBookFeatured();

    Book save(Book book);

    Book update(Book book);

    FeaturedBookDTO findById(long id);

    List<Book> findByPromotion(long promotionId);

    List<Book> findByBlackList(long promotionId);

    Book findById2(long id);

    void deleteById(long id);

    void updateStatusBook(long id);

    BookResponse findPaginatedByCategory(int pageNo, int pageSize, String sortBy, String sortDir,Long categoryId);


}
