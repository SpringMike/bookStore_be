package com.example.demo.repo;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepo extends JpaRepository<Category,Long> {
    @Query(nativeQuery = true,value = "select *\n" +
            "from category\n" +
            "where not EXISTS(\n" +
            "              select c.*\n" +
            "              from category c\n" +
            "                       inner join promotion_categories pc on c.id = pc.category_id\n" +
            "              where c.id = category.id\n" +
            "                and promotion_id = ?1\n" +
            "          ) and not EXISTS(\n" +
            "        select c.*\n" +
            "        from category c\n" +
            "                 inner join promotion_categories pc on c.id = pc.category_id\n" +
            "        where c.id = category.id\n" +
            "    )")
    List<Category> findByPromotionInclude(Long promotionId);

    @Query(nativeQuery = true,value = "select * from category inner join promotion_categories pc on category.id = pc.category_id where promotion_id = ?1 ")
    List<Category> findAllByPromotionInclude(Long promotionId);
}
