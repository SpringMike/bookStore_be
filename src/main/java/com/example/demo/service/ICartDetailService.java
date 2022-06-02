package com.example.demo.service;

import com.example.demo.dto.CartDetailDTO;
import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.model.Book;
import com.example.demo.model.CartDetail;

import java.util.List;

public interface ICartDetailService {
    List<CartDetailDTO> getAllCartDetailFeaturedByOrderId(long cartId);

    CartDetail save(CartDetail cartDetail);

    CartDetail update(CartDetail cartDetail);

    CartDetail findById(long id);

    void deleteById(List<Integer> id);
}
