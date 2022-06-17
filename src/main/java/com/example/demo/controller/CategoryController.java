package com.example.demo.controller;

import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.model.Account;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.service.impl.BookService;
import com.example.demo.service.impl.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategory();
    }

    @GetMapping("{id}")
    public Optional<Category> getById(@PathVariable int id){
        return categoryService.findById(id);
    }


    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Category updateCategory(@RequestBody Category category){
        return categoryService.update(category);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Category saveProduct(@RequestBody Category category){
        return categoryService.save(category);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable long id){
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updateStatus/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Category updateStatus(@PathVariable long id) {
        return categoryService.updateStatus(id);
    }

    @GetMapping("findAllByPromotionInclude/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Category> getAllPromotionIncludeCate(@PathVariable Long id){
        return categoryService.findByPromotionInclude(id);
    }
    @GetMapping("findByPromotionInclude/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Category> getPromotionIncludeCate(@PathVariable Long id){
        return categoryService.findAllByPromotionInclude(id);
    }
}
