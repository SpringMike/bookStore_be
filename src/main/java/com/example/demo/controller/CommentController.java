package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.FavoriteDTO;
import com.example.demo.model.Comment;
import com.example.demo.service.ICommentService;
import com.example.demo.service.impl.CommentService;
import com.example.demo.service.impl.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final ICommentService commentService;

    @GetMapping
    public List<Comment> getAllComment(){
        return commentService.getAllComment();
    }

    @GetMapping("/{bookId}")
    public List<Comment> getAllCommentByBook(@PathVariable long bookId){
        return commentService.getAllCommentByBook(bookId);
    }
    @PostMapping
    public void postComment(@RequestBody Comment comment){
         commentService.save(comment);
    }
}
