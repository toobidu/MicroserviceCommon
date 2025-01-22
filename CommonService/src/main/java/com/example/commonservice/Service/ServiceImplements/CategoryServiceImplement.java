package com.example.commonservice.Service.ServiceImplements;

import com.example.commonservice.Model.Entity.Category;
import com.example.commonservice.Repository.CategoryRepository;
import com.example.commonservice.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
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
    @Transactional(readOnly = false)  // Cho phép ghi dữ liệu
    public Category createCategory(Category category) {
        try {
            // Đảm bảo ID là null khi tạo mới
            category.setCategoryId(null);

            // Set các giá trị mặc định nếu chưa có
            if (category.getStatus() == null) {
                category.setStatus(true);
            }

            // Lưu entity
            return categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            // Xử lý lỗi unique constraint (nếu có)
            throw new RuntimeException("Category code already exists", e);
        } catch (Exception e) {
            throw new RuntimeException("Error creating category", e);
        }
    }

    // Update a category
    @Override
    @Transactional(readOnly = false)
    public Optional<Category> updateCategory(Long categoryId, Category category) {
        try {
            return categoryRepository.findById(categoryId)
                    .map(existingCategory -> {
                        // Cập nhật từng trường một thay vì save trực tiếp
                        existingCategory.setCategoryCode(category.getCategoryCode());
                        existingCategory.setCategoryName(category.getCategoryName());
                        existingCategory.setStatus(category.getStatus());
                        existingCategory.setCreatedUser(category.getCreatedUser());
                        existingCategory.setUpdatedUser(category.getUpdatedUser());
                        return categoryRepository.save(existingCategory);
                    });
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Category code already exists", e);
        } catch (Exception e) {
            throw new RuntimeException("Error updating category", e);
        }
    }

    // Delete a category
    @Override
    @Transactional(readOnly = false)
    public boolean deleteCategory(Long categoryId) {
        try {
            if (categoryRepository.existsById(categoryId)) {
                categoryRepository.deleteById(categoryId);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting category", e);
        }
    }

    // Search categories by multiple criteria
    @Override
    public List<Category> searchCategories(Long categoryId, String categoryCode, String categoryName, Boolean status, Date createdTime, Date updatedTime, Long createdUser, Long updatedUser) {
        // Implement the search logic based on the criteria
        return categoryRepository.searchCategoriesBy(categoryId, categoryCode, categoryName, status, createdTime, updatedTime, createdUser, updatedUser);
    }
}
