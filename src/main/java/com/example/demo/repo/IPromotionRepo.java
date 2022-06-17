package com.example.demo.repo;

import com.example.demo.model.Category;
import com.example.demo.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPromotionRepo extends JpaRepository<Promotion,Long> {

}
