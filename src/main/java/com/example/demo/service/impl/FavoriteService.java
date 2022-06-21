package com.example.demo.service.impl;

import com.example.demo.dto.FavoriteDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Favorite;
import com.example.demo.model.Publisher;
import com.example.demo.repo.IFavoriteRepo;
import com.example.demo.service.IFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService implements IFavoriteService {

    private final IFavoriteRepo favoriteRepo;

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<FavoriteDTO> getAllFavoriteByAccountId(long accountId) {
        String sql ="select b.id        id,\n" +
                "       b.back_cover_image as backCoverImage,\n" +
                "       b.front_cover_image as frontCoverImage,\n" +
                "       b.name   as bookName,\n" +
                "       b.price\n" +
                "from book b\n" +
                "         inner join favorite f on b.id = f.book_id\n" +
                "where account_id = ?1 and f.status = 'true'";

        Query query = this.entityManager.createNativeQuery(sql.toString(),"FeaturedFavoriteMapping");
        query.setParameter(1,accountId);
        return query.getResultList();
    }

    @Override
    public Favorite save(Favorite favorite) {
        return favoriteRepo.save(favorite);
    }

    @Override
    public Favorite update(Favorite favorite) {
        Favorite favoriteFromDb = favoriteRepo.findByAccountIdAndBookId(favorite.getAccountId(),favorite.getBookId());
        if (favoriteFromDb != null){
            favoriteFromDb.setStatus(favorite.isStatus());
            favoriteRepo.save(favoriteFromDb);
        }
        return null;
    }

    @Override
    public Favorite findById(long accountId,long bookId) {
        return favoriteRepo.findByAccountIdAndBookId(accountId,bookId);
    }

    @Override
    public void deleteById(long id) {

    }
}
