package com.example.commonservice.Service.ServiceImplements;

import com.example.commonservice.Model.Entity.Category;
import com.example.commonservice.Repository.CategoryRepository;
import com.example.commonservice.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
@Service
public class CategoryServiceImplement implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImplement(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Get all
    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    // Create new
    @Override
    @Transactional
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Update
    @Override
    @Transactional
    public Optional<Category> updateCategory(Long categoryId, Category category) {
        if (categoryRepository.existsById(categoryId)) {
            category.setCategoryId(categoryId);
            return Optional.of(categoryRepository.save(category));
        }
        return Optional.empty();
    }

    // Delete
    @Override
    @Transactional
    public boolean deleteCategory(Long categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
            return true;
        }
        return false;
    }

    // Repository methods
    @Override
    public Optional<Category> findByCategoryId(Long categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }

    @Override
    public Optional<Category> findByCategoryCode(String categoryCode) {
        return categoryRepository.findByCategoryCode(categoryCode);
    }

    @Override
    public Optional<Category> findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<Category> findByStatus(Boolean status) {
        return categoryRepository.findByStatus(status);
    }

    @Override
    public List<Category> findByCreatedTime(Date createdTime) {
        return categoryRepository.findByCreatedTime(createdTime);
    }

    @Override
    public List<Category> findByUpdatedTime(Date updatedTime) {
        return categoryRepository.findByUpdatedTime(updatedTime);
    }

    @Override
    public List<Category> findByCreatedUser(Long createdUser) {
        return categoryRepository.findByCreatedUser(createdUser);
    }

    @Override
    public List<Category> findByUpdatedUser(Long updatedUser) {
        return categoryRepository.findByUpdatedUser(updatedUser);
    }
}
