package com.example.commonservice.Controller;

import com.example.commonservice.Model.Entity.Category;
import com.example.commonservice.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/common/front-end/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Get all categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    // Create a new category
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody @Valid Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(createdCategory);
    }

    // Update a category
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody @Valid Category category) {
        Optional<Category> updatedCategory = categoryService.updateCategory(id, category);
        return updatedCategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a category
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted) {
            return ResponseEntity.ok("Category is deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error!");
        }
    }

    // Search categories by multiple criteria
    @GetMapping("/search")
    public ResponseEntity<List<Category>> searchCategories(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String categoryCode,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date updatedTime,
            @RequestParam(required = false) Long createdUser,
            @RequestParam(required = false) Long updatedUser) {

        List<Category> categories = categoryService.searchCategories(categoryId, categoryCode, categoryName, status, createdTime, updatedTime, createdUser, updatedUser);
        return ResponseEntity.ok(categories);
    }
    //mapping sequence id springboot
}


