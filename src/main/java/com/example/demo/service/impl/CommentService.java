package com.example.demo.service.impl;

import com.example.demo.dto.CommentDTO;
import com.example.demo.model.Account;
import com.example.demo.model.Comment;
import com.example.demo.repo.ICommentRepo;
import com.example.demo.service.IAccountService;
import com.example.demo.service.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class CommentService implements ICommentService {

    private final ICommentRepo commentRepo;
    private final AccountService accountService;

    @Override
    public List<Comment> getAllComment() {
        return commentRepo.findAll();
    }

    @Override
    public List<Comment> getAllCommentByBook(long bookId) {
        return commentRepo.findByBookIdAndIsParentTrue(bookId);
    }

    @Override
    public void save(Comment comment) {
        Comment currentComment = commentRepo.save(comment);
        //check no cho phai parent comment khong
        if (currentComment.getParentId() != null) {
            Comment parent = commentRepo.findById(currentComment.getParentId()).orElse(null);
            if (parent != null) {
                parent.addSubComment(currentComment);
                currentComment.setParentId(parent.getId());
                currentComment.setParentComment(parent);
                commentRepo.save(parent);
                commentRepo.save(currentComment);
            }
        }
    }

    @Override
    public Comment update(Comment comment) {
        return null;
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(long id) {

    }
}
