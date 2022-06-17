package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.model.Category;
import com.example.demo.model.Promotion;

import java.util.List;
import java.util.Optional;

public interface IPromotionService {
    List<Promotion> getAllPromotion();

    Promotion save(Promotion promotion);

    Promotion addCategoryToPromotion(Long promotionId,Long cateId);

    Promotion addProductToBlackList(Long promotionId,Long bookId);

    void deleteByIdAndBookId(long id,long bookId);

    Promotion update(Promotion promotion);

    Optional<Promotion> findById(long id);

    void deleteByIdAndCateId(long id,long cateId);
}
