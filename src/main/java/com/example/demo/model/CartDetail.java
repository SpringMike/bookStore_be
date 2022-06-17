package com.example.demo.model;

import com.example.demo.dto.CartDetailDTO;
import com.example.demo.dto.FeaturedBookDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(
        name = "getFeaturedOrderDetailByCartId",
        query = "select c.id                                                                              as id,\n" +
                "       c.book_id                                                                         as idBook,\n" +
                "       c.cart_id                                                                         as idCart,\n" +
                "       b.name                                                                            as nameBook,\n" +
                "       b.front_cover_image                                                               as frontImage,\n" +
                "       b.back_cover_image                                                                as backImage,\n" +
                "       b.price                                                                           as price,\n" +
                "       c.quantity                                                                        as quantity,\n" +
                "       (c.quantity * price)                                                              as total,\n" +
                "       b.price - (CAST(b.price as float) * (CAST(p.sale as float) / 100))                as newPrice,\n" +
                "       (b.price - (CAST(b.price as float) * (CAST(p.sale as float) / 100))) * c.quantity as newTotal,\n" +
                "       pbl.promotion_id as promotionBlackListId,\n" +
                "       p.sale as sale\n" +
                "from cart_detail c\n" +
                "         inner join book b on b.id = c.book_id\n" +
                "         inner join category c2 on b.category_id = c2.id\n" +
                "         left join promotion_categories pc on c2.id = pc.category_id\n" +
                "         left join promotion p on pc.promotion_id = p.id\n" +
                "        left join promotion_black_list pbl on b.id = pbl.book_id\n" +
                "where c.cart_id = ?1",
        resultSetMapping = "FeaturedCartDetailMapping"
)
@SqlResultSetMapping(
        name = "FeaturedCartDetailMapping",
        classes = {
                @ConstructorResult(
                        targetClass = CartDetailDTO.class,
                        columns = {
                                @ColumnResult(name="id", type = Long.class),
                                @ColumnResult(name="idBook", type = Long.class),
                                @ColumnResult(name="idCart", type = Long.class),
                                @ColumnResult(name="nameBook", type = String.class),
                                @ColumnResult(name="frontImage", type = String.class),
                                @ColumnResult(name="backImage", type = String.class),
                                @ColumnResult(name="price", type = Double.class),
                                @ColumnResult(name="quantity", type = Integer.class),
                                @ColumnResult(name="total", type = Double.class),
                                @ColumnResult(name="newPrice", type = Float.class),
                                @ColumnResult(name="newTotal", type = Float.class),
                                @ColumnResult(name="promotionBlackListId", type = Long.class),
                                @ColumnResult(name="sale", type = Integer.class),
                        }
                )
        }
)
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bookId", insertable = false,updatable = false)
    private Book book;
    private Long bookId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cartId", insertable = false,updatable = false)
    private Cart cart;
    private Long cartId;
}
