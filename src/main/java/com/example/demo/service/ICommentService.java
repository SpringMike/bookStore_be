package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Comment;
import com.example.demo.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface ICommentService {

    List<Comment> getAllComment();

    List<Comment> getAllCommentByBook(long bookId);

    void save(Comment comment);

    Comment update(Comment comment);

    Optional<Comment> findById(long id);

    void deleteById(long id);
}
