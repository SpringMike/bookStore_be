package com.example.demo.model;

import com.example.demo.dto.FavoriteDTO;
import com.example.demo.dto.FeaturedBookDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SqlResultSetMapping(
        name = "FeaturedFavoriteMapping",
        classes = {
                @ConstructorResult(
                        targetClass = FavoriteDTO.class,
                        columns = {
                                @ColumnResult(name="id", type = Long.class),
                                @ColumnResult(name="backCoverImage", type = String.class),
                                @ColumnResult(name="frontCoverImage", type = String.class),
                                @ColumnResult(name="bookName", type = String.class),
                                @ColumnResult(name="price", type = Float.class),
                        }
                )
        }
)
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "accountId", insertable = false,updatable = false)
    private Account account;
    private Long accountId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bookId", insertable = false,updatable = false)
    private Book book;
    private Long bookId;
}
