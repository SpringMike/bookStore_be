package com.example.demo.repo;

import com.example.demo.dto.CartDetailDTO;
import com.example.demo.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ICartDetailRepo extends JpaRepository<CartDetail,Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "delete from cart_detail where id in (?1)")
    void deleteByIds(List<Integer> id);

}
