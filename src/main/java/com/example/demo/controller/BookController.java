package com.example.demo.controller;

import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.model.Book;
import com.example.demo.service.impl.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

//    @GetMapping("/bookId/{id}")
//    public List<Book> getProductByIdCate(@PathVariable int id){
//        return bookService.findProductByIdCategory(id);
//    }

    @GetMapping
    public List<FeaturedBookDTO> getAllBook(){
        return bookService.getAllBookFeatured();
    }

    @GetMapping("{id}")
    public Optional<Book> getById(@PathVariable int id){
        return bookService.findById(id);
    }

    @PutMapping
    public Book updateProduct(@RequestBody Book book){
        return bookService.update(book);
    }

    @PostMapping
    public Book saveProduct(@RequestBody Book book){
        return bookService.save(book);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable long id){
        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
