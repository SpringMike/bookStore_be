package com.example.demo.model;

import com.example.demo.dto.FeaturedBookDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.security.PublicKey;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(
        name = "getFeaturedBook",
        query = "select b.id id, b.back_cover_image, b.front_cover_image, b.description, b.language,b.name as bookName,b.number_page,b.price,b.public_year,\n" +
                "       b.quantity,b.status as bookStatus,a.name as authorName , c.name as categoryName, p.name as publisherName, s.name as supplierName\n" +
                "from book b inner join author a on a.id = b.author_id\n" +
                "            inner join category c on c.id = b.category_id\n" +
                "            inner join publisher p on p.id = b.publisher_id\n" +
                "            inner join supplier s on s.id = b.supplier_id",
        resultSetMapping = "FeaturedBookMapping"
)
@SqlResultSetMapping(
        name = "FeaturedBookMapping",
        classes = {
                @ConstructorResult(
                        targetClass = FeaturedBookDTO.class,
                        columns = {
                                @ColumnResult(name="id", type = Long.class),
                                @ColumnResult(name="back_cover_image", type = String.class),
                                @ColumnResult(name="front_cover_image", type = String.class),
                                @ColumnResult(name="description", type = String.class),
                                @ColumnResult(name="language", type = String.class),
                                @ColumnResult(name="bookName", type = String.class),
                                @ColumnResult(name="number_page", type = Integer.class),
                                @ColumnResult(name="price", type = Float.class),
                                @ColumnResult(name="public_year", type = String.class),
                                @ColumnResult(name="quantity", type = Integer.class),
                                @ColumnResult(name="bookStatus", type = Boolean.class),
                                @ColumnResult(name="authorName", type = String.class),
                                @ColumnResult(name="categoryName", type = String.class),
                                @ColumnResult(name="publisherName", type = String.class),
                                @ColumnResult(name="supplierName", type = String.class),
                        }
                )
        }
)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private float price;
    private String description;
    private String frontCoverImage;
    private String backCoverImage;
    private int quantity;
    private int numberPage;
    private String publicYear;
    private String language;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "authorId", insertable = false,updatable = false)
    private Author author;
    private Long authorId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoryId", insertable = false,updatable = false)
    private Category category;
    private Long categoryId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "publisherId", insertable = false,updatable = false)
    private Publisher publisher;
    private Long publisherId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "supplierId", insertable = false,updatable = false)
    private Supplier supplier;
    private Long supplierId;

    private boolean status;
}
