package com.example.demo.controller;

import com.example.demo.dto.BookResponse;
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

    @GetMapping("/getAllBook")
    public List<FeaturedBookDTO> getAllBook() {
        return bookService.getAllBookFeatured();
    }

    @GetMapping("{id}")
    public FeaturedBookDTO getById(@PathVariable long id) {
        return bookService.findById(id);
    }
    @GetMapping("/updateBook/{id}")
    public Book getById2(@PathVariable long id) {
        return bookService.findById2(id);
    }

    @PutMapping
    public Book updateProduct(@RequestBody Book book) {
        return bookService.update(book);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<HttpStatus> updateStatusProduct(@PathVariable Long id) {
        bookService.updateStatusBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public Book saveProduct(@RequestBody Book book) {
        return bookService.save(book);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable long id) {
        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public BookResponse getPaginatedBooks(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "8", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir,
             @RequestParam(value = "categoryId", required = true) String categoryId
    ){
        return bookService.findPaginatedByCategory(pageNo, pageSize, sortBy, sortDir,Long.parseLong(categoryId));
    }

}
