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
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(
        name = "getFeaturedBook",
        query = "select b.id  as id,\n" +
                "       b.back_cover_image,\n" +
                "       b.front_cover_image,\n" +
                "       b.description,\n" +
                "       b.language,\n" +
                "       b.name   as bookName,\n" +
                "       b.number_page,\n" +
                "       b.public_year,\n" +
                "       b.quantity,\n" +
                "       b.status as bookStatus,\n" +
                "       a.name   as authorName,\n" +
                "       c.name   as categoryName,\n" +
                "       pu.name   as publisherName,\n" +
                "       s.name   as supplierName,\n" +
                "       b.price as price,\n" +
                "       p.sale as sale,\n" +
                "       b.price - ( CAST(b.price as float) * (CAST(p.sale as float) / 100 )) as newPrice,\n" +
                "       pbl.promotion_id as blackListPromotionId\n" +
                "from promotion p\n" +
                "         right join promotion_categories pc on p.id = pc.promotion_id\n" +
                "         right join category c on c.id = pc.category_id\n" +
                "         right join book b on c.id = b.category_id\n" +
                "         inner join author a on a.id = b.author_id\n" +
                "         inner join publisher pu on pu.id = b.publisher_id\n" +
                "         inner join supplier s on s.id = b.supplier_id\n" +
                "         left join promotion_black_list pbl on b.id = pbl.book_id\n",
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
                                @ColumnResult(name="public_year", type = String.class),
                                @ColumnResult(name="quantity", type = Integer.class),
                                @ColumnResult(name="bookStatus", type = Boolean.class),
                                @ColumnResult(name="authorName", type = String.class),
                                @ColumnResult(name="categoryName", type = String.class),
                                @ColumnResult(name="publisherName", type = String.class),
                                @ColumnResult(name="supplierName", type = String.class),
                                @ColumnResult(name="price", type = Float.class),
                                @ColumnResult(name="sale", type = Integer.class),
                                @ColumnResult(name="newPrice", type = Float.class),
                                @ColumnResult(name="blackListPromotionId", type = Integer.class),
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

    @JsonIgnore
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<CartDetail> cartDetails;

    @JsonIgnore
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @JsonIgnore
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Favorite> favorites;

    @JsonIgnore
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Comment> comments;

    private boolean status;
}
