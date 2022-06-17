package com.example.demo.repo;

import com.example.demo.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepo extends JpaRepository<Book,Long> {
    @Query(nativeQuery = true, value = "\n" +
            "select count(*) as total from book")
    int getTotal();

    @Query(nativeQuery = true,value = "select b.*,c.name\n" +
            "from promotion p\n" +
            "         inner join promotion_categories pc on p.id = pc.promotion_id\n" +
            "         inner join category c on c.id = pc.category_id\n" +
            "         inner join book b on c.id = b.category_id\n" +
            "where not exists(\n" +
            "        select * from promotion_black_list pb where p.id = pb.promotion_id and b.id = pb.book_id\n" +
            "    ) and promotion_id = ?1")
    List<Book> findByPromotionId(long promotionId);


    Page<Book> findByCategoryId(Long categoryId,Pageable pageable);

    @Query(nativeQuery = true,value = "select * from book inner join promotion_black_list pbl on book.id = pbl.book_id\n" +
            "where promotion_id=?1")
    List<Book> findByBlackList(long promotionId);
}
