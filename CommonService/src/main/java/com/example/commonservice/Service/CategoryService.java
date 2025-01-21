package com.example.commonservice.Service;

import com.example.commonservice.Model.Entity.Category;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

    // Get all categories
    List<Category> getAllCategory();

    // Create a new category
    Category createCategory(Category category);

    // Update a category
    Optional<Category> updateCategory(Long categoryId, Category category);

    // Delete a category
    boolean deleteCategory(Long categoryId);

    // Search categories by multiple criteria
    List<Category> searchCategories(Long categoryId, String categoryCode, String categoryName, Boolean status, Date createdTime, Date updatedTime, Long createdUser, Long updatedUser);

}
