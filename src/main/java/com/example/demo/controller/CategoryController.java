package com.example.demo.controller;

import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.service.impl.BookService;
import com.example.demo.service.impl.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Category> getAllBook(){
        return categoryService.getAllCategory();
    }

    @GetMapping("{id}")
    public Optional<Category> getById(@PathVariable int id){
        return categoryService.findById(id);
    }

    @PutMapping
    public Category updateProduct(@RequestBody Category category){
        return categoryService.update(category);
    }

    @PostMapping
    public Category saveProduct(@RequestBody Category category){
        return categoryService.save(category);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable long id){
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
