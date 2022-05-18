package com.example.demo.service.impl;

import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.model.Book;
import com.example.demo.repo.IBookRepo;
import com.example.demo.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final IBookRepo bookRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FeaturedBookDTO> getAllBookFeatured() {
        List<FeaturedBookDTO> listFeaturedBook = entityManager.createNamedQuery("getFeaturedBook").getResultList();
        return listFeaturedBook;
    }

    @Override
    public Book save(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Book update(Book book) {
        Book bookFromDb = bookRepo.findById(book.getId()).orElse(null);
        if (bookFromDb!= null){
            bookFromDb.setName(book.getName());
            bookFromDb.setPrice(book.getPrice());
            bookFromDb.setDescription(book.getDescription());
            bookFromDb.setFrontCoverImage(book.getFrontCoverImage());
            bookFromDb.setBackCoverImage(book.getBackCoverImage());
            bookFromDb.setQuantity(book.getQuantity());
            bookFromDb.setNumberPage(book.getNumberPage());
            bookFromDb.setPublicYear(book.getPublicYear());
            bookFromDb.setAuthorId(book.getAuthorId());
            bookFromDb.setCategoryId(book.getCategoryId());
            bookFromDb.setSupplierId(book.getSupplierId());
            bookFromDb.setPublisherId(book.getPublisherId());
            bookRepo.save(bookFromDb);
        }
        return null;
    }

    @Override
    public Optional<Book> findById(long id) {
        if (id < 0){
            return null;
        }
        return bookRepo.findById(id);
    }

    @Override
    public void deleteById(long id) {
        bookRepo.deleteById(id);
    }
}
