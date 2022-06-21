package com.example.demo.service;

import com.example.demo.dto.FavoriteDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Favorite;
import com.example.demo.model.Publisher;

import java.util.List;
import java.util.Optional;

public interface IFavoriteService {
    List<FavoriteDTO> getAllFavoriteByAccountId(long accountId);

    Favorite save(Favorite favorite);

    Favorite update(Favorite favorite);

    Favorite findById(long accountId,long bookId);

    void deleteById(long id);
}
