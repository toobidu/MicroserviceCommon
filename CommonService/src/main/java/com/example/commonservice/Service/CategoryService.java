package com.example.commonservice.Service;

import com.example.commonservice.Model.Entity.Category;

import java.util.*;
public interface CategoryService {

    // Get all
    List<Category> getAllCategory();

    // Create new
    Category createCategory(Category category);

    // Update
    Optional<Category> updateCategory(Long categoryId, Category category);

    // Delete
    boolean deleteCategory(Long categoryId);

    // Repository
    Optional<Category> findByCategoryId(Long categoryId);

    List<Category> findByCategoryCode(String categoryCode);

    List<Category> findByCategoryName(String categoryName);

    List<Category> findByStatus(Boolean status);

    List<Category> findByCreatedTime(Date createdTime);

    List<Category> findByUpdatedTime(Date updatedTime);

    List<Category> findByCreatedUser(Long createdUser);

    List<Category> findByUpdatedUser(Long updatedUser);
}
