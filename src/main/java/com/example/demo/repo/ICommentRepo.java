package com.example.demo.repo;

import com.example.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepo extends JpaRepository<Comment,Long> {
    List<Comment> findByBookIdAndIsParentTrue(long bookId);
}
