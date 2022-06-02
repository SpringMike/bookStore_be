package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.repo.ICategoryRepo;
import com.example.demo.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final ICategoryRepo categoryRepo;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category update(Category category) {
        Category categoryFromDb = categoryRepo.findById(category.getId()).orElse(null);
        if (categoryFromDb != null){
            categoryFromDb.setName(category.getName());
            categoryFromDb.setNote(category.getNote());
            categoryRepo.save(categoryFromDb);
        }
        return null;
    }

    @Override
    public Category updateStatus(long id) {
        Category categoryFromDb = categoryRepo.findById(id).orElse(null);
        if (categoryFromDb!= null){
            if (categoryFromDb.isStatus()){
                categoryFromDb.setStatus(false);
                categoryRepo.save(categoryFromDb);
            }else {
                categoryFromDb.setStatus(true);
                categoryRepo.save(categoryFromDb);
            }
        }
        return null;
    }

    @Override
    public Optional<Category> findById(long id) {
        if (id < 0){
            return Optional.empty();
        }
        return categoryRepo.findById(id);
    }

    @Override
    public void deleteById(long id) {
        categoryRepo.deleteById(id);
    }
}
