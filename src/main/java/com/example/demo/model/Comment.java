package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private Set<Comment> subComments = new HashSet<>();

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Comment parentComment;
    private Long parentId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bookId", insertable = false,updatable = false)
    private Book book;
    private Long bookId;

    private String comment;
    private boolean isParent;

    private String accountName;

    public Comment addSubComment(Comment comment) {
        this.subComments.add(comment);
        return this;
    }
}
