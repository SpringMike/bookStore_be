package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repo.IBookRepo;
import com.example.demo.repo.ICategoryRepo;
import com.example.demo.repo.IPromotionRepo;
import com.example.demo.service.IPromotionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class PromotionService implements IPromotionService {
    private final IPromotionRepo promotionRepo;
    private final ICategoryRepo categoryRepo;
    private final IBookRepo bookRepo;
    @Override
    public List<Promotion> getAllPromotion() {
        return promotionRepo.findAll();
    }

    @Override
    public Promotion save(Promotion promotion) {
        return promotionRepo.save(promotion);
    }

    @Override
    public Promotion addCategoryToPromotion(Long promotionId,Long cateId) {
        Promotion promotionFromDB = promotionRepo.findById(promotionId).orElse(null);
        if (promotionFromDB != null) {
            Category category1 = categoryRepo.findById(cateId).get();
            Set<Category> categories = new HashSet<>(promotionFromDB.getCategories());
            categories.add(category1);
            promotionFromDB.setCategories(categories);
            promotionRepo.save(promotionFromDB);
        }
        return null;
    }

    @Override
    public Promotion addProductToBlackList(Long promotionId, Long bookId) {
        Promotion promotionFromDB = promotionRepo.findById(promotionId).orElse(null);
        if (promotionFromDB != null) {
            Book book1 = bookRepo.findById(bookId).get();
            Set<Book> books = new HashSet<>(promotionFromDB.getBlackListBook());
            books.add(book1);
            promotionFromDB.setBlackListBook(books);
            promotionRepo.save(promotionFromDB);
        }
        return null;
    }

    @Override
    public void deleteByIdAndBookId(long id, long bookId) {
        Promotion promotionFromDB = promotionRepo.findById(id).orElse(null);
        if (promotionFromDB != null) {
            promotionFromDB.getBlackListBook().remove(bookRepo.findById(bookId).get());
            promotionRepo.save(promotionFromDB);
        }
    }

    @Override
    public Promotion update(Promotion promotion) {
        return null;
    }

    @Override
    public Optional<Promotion> findById(long id) {
        return promotionRepo.findById(id);
    }

    @Override
    public void deleteByIdAndCateId(long id,long cateId) {
        Promotion promotionFromDB = promotionRepo.findById(id).orElse(null);
        if (promotionFromDB != null) {
            promotionFromDB.getCategories().remove(categoryRepo.findById(cateId).get());
            promotionRepo.save(promotionFromDB);
        }
    }
}
