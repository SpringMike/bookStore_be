package com.example.demo.service.impl;

import com.example.demo.dto.CartDetailDTO;
import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.model.Book;
import com.example.demo.model.CartDetail;
import com.example.demo.repo.ICartDetailRepo;
import com.example.demo.service.ICartDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@AllArgsConstructor
public class CartDetailService implements ICartDetailService {

    private final ICartDetailRepo cartDetailRepo;

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<CartDetailDTO> getAllCartDetailFeaturedByOrderId(long cartId) {
        String sql = "select c.id                as id,\n" +
                "       c.book_id           as idBook,\n" +
                "       c.cart_id           as idCart,\n" +
                "       b.name              as nameBook,\n" +
                "       b.front_cover_image as frontImage,\n" +
                "       b.back_cover_image  as backImage,\n" +
                "       b.price as price,\n" +
                "       c.quantity as quantity,\n" +
                "       (c.quantity*price) as total\n" +
                "from cart_detail c\n" +
                "         inner join book b on b.id = c.book_id\n" +
                "where c.cart_id = ?1";
        Query query = this.entityManager.createNativeQuery(sql.toString(),"FeaturedCartDetailMapping");
        query.setParameter(1,cartId);
        return query.getResultList();
    }

    @Override
    public CartDetail save(CartDetail cartDetail) {
        return cartDetailRepo.save(cartDetail);
    }

    @Override
    public CartDetail update(CartDetail cartDetail) {
        CartDetail cartDetailFromDB = cartDetailRepo.findById(cartDetail.getId()).orElse(null);
        if (cartDetailFromDB != null) {
            cartDetailFromDB.setQuantity(cartDetail.getQuantity());
            cartDetailRepo.save(cartDetailFromDB);
        }
        return null;
    }

    @Override
    public CartDetail findById(long id) {
        return null;
    }


    @Override
    public void deleteById(List<Integer> id) {
        cartDetailRepo.deleteByIds(id);
    }
}