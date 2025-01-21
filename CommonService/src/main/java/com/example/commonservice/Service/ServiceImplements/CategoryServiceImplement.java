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

    // Get all categories
    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    // Create a new category
    @Override
    @Transactional
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Update a category
    @Override
    @Transactional
    public Optional<Category> updateCategory(Long categoryId, Category category) {
        if (categoryRepository.existsById(categoryId)) {
            category.setCategoryId(categoryId);
            return Optional.of(categoryRepository.save(category));
        }
        return Optional.empty();
    }

    // Delete a category
    @Override
    @Transactional
    public boolean deleteCategory(Long categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
            return true;
        }
        return false;
    }

    // Search categories by multiple criteria
    @Override
    public List<Category> searchCategories(Long categoryId, String categoryCode, String categoryName, Boolean status, Date createdTime, Date updatedTime, Long createdUser, Long updatedUser) {
        // Implement the search logic based on the criteria
        return categoryRepository.searchCategoriesBy(categoryId, categoryCode, categoryName, status, createdTime, updatedTime, createdUser, updatedUser);
    }
}
