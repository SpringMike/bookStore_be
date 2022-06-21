package com.example.demo.repo;

import com.example.demo.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFavoriteRepo extends JpaRepository<Favorite,Long> {
    List<Favorite> findByAccountId(long accountId);

    Favorite findByAccountIdAndBookId(Long accountId,long bookId);
}
