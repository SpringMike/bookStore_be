package com.example.demo.repo;

import com.example.demo.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPublisherRepo extends JpaRepository<Publisher,Long> {
}
