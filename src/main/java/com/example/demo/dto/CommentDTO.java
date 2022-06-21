package com.example.demo.dto;

import com.example.demo.model.Account;
import com.example.demo.model.Book;
import com.example.demo.model.Comment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    @Id
    private Long id;

    private Set<CommentDTO> subComments = new HashSet<>();

    private Long parentId;

    private String accountName;

    private Long bookId;

    private String comment;

    private boolean isParent;
}
