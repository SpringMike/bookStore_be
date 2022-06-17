package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.Category;
import com.example.demo.model.Promotion;
import com.example.demo.service.ICartRepo;
import com.example.demo.service.impl.AccountService;
import com.example.demo.service.impl.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/promotions")
@AllArgsConstructor
public class PromotionController {
    private final PromotionService promotionService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Promotion> getAllPromotion() {
        return promotionService.getAllPromotion();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Promotion save(@RequestBody Promotion promotion) {
        return promotionService.save(promotion);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Promotion findById(@PathVariable long id) {
        return promotionService.findById(id).get();
    }

    @PutMapping("/addCategoryToPromotion/{id}/{cateId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Promotion addCategoryToPromotion(@PathVariable Long id, @PathVariable Long cateId) {
        return promotionService.addCategoryToPromotion(id, cateId);
    }

    @PutMapping("/addBookToBlackList/{id}/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Promotion addBookToBlackList(@PathVariable Long id, @PathVariable Long bookId) {
        return promotionService.addProductToBlackList(id, bookId);
    }

    @DeleteMapping("/deleteBookFromBlackList/{id}/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBookFromBlackList(@PathVariable Long id, @PathVariable Long bookId) {
        promotionService.deleteByIdAndBookId(id, bookId);
    }

    @DeleteMapping("/deleteCategoryFromPromotion/{id}/{cateId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategoryFromPromotion(@PathVariable Long id, @PathVariable Long cateId) {
        promotionService.deleteByIdAndCateId(id, cateId);
    }
}
