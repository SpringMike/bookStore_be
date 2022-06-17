package com.example.demo.service;

import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.model.Book;
import com.example.demo.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    List<Category> getAllCategory();

    Category save(Category category);

    Category update(Category category);

    Category updateStatus(long id);

    List<Category> findByPromotionInclude(Long promotionId);

    List<Category> findAllByPromotionInclude(Long promotionId);

    Optional<Category> findById(long id);

    void deleteById(long id);
}
