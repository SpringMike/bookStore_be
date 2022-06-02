package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.model.Publisher;
import com.example.demo.service.impl.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    @GetMapping
    public List<Author> getAllAuthor(){
        return authorService.getAllAuthor();
    }
}
