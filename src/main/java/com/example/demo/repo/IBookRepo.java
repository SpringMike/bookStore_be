package com.example.demo.repo;

import com.example.demo.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepo extends JpaRepository<Book,Long> {
    @Query(nativeQuery = true, value = "\n" +
            "select count(*) as total from book")
    int getTotal();



    Page<Book> findByCategoryId(Long categoryId,Pageable pageable);
}
