package com.example.commonservice.Controller;

import com.example.commonservice.Model.DTO.CategoryDTO;
import com.example.commonservice.Model.Entity.Category;
import com.example.commonservice.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/common/front-end/category")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Get all
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        return ResponseEntity.ok(convertToDTOList(categories));
    }

    // Create a new category
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(convertToDTO(createdCategory));
    }

    // Update a category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Optional<Category> updatedCategory = categoryService.updateCategory(id, category);
        return updatedCategory.map(value -> ResponseEntity.ok(convertToDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
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

    // Find by id
    @GetMapping("/category-id/{categoryId}")
    public ResponseEntity<List<CategoryDTO>> findByCategoryId(@PathVariable Long categoryId) {
        Optional<Category> category = categoryService.findByCategoryId(categoryId);
        return category.map(value -> ResponseEntity.ok(List.of(convertToDTO(value)))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Find by category code
    @GetMapping("/category-code/{categoryCode}")
    public ResponseEntity<List<CategoryDTO>> findByCategoryCode(@PathVariable String categoryCode) {
        List<Category> categories = categoryService.findByCategoryCode(categoryCode);
        return ResponseEntity.ok(convertToDTOList(categories));
    }

    // Find by name
    @GetMapping("/category-name/{categoryName}")
    public ResponseEntity<List<CategoryDTO>> findByCategoryName(@PathVariable String categoryName) {
        List<Category> categories = categoryService.findByCategoryName(categoryName);
        return ResponseEntity.ok(convertToDTOList(categories));
    }

    // Find by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<CategoryDTO>> findByStatus(@PathVariable Boolean status) {
        List<Category> categories = categoryService.findByStatus(status);
        return ResponseEntity.ok(convertToDTOList(categories));
    }

    // Find categories by created time
    @GetMapping("/created-time/{createdTime}")
    public ResponseEntity<List<CategoryDTO>> findByCreatedTime(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdTime) {
        List<Category> categories = categoryService.findByCreatedTime(createdTime);
        return ResponseEntity.ok(convertToDTOList(categories));
    }

    // Find categories by updated time
    @GetMapping("/updated-time/{updatedTime}")
    public ResponseEntity<List<CategoryDTO>> findByUpdatedTime(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date updatedTime) {
        List<Category> categories = categoryService.findByUpdatedTime(updatedTime);
        return ResponseEntity.ok(convertToDTOList(categories));
    }

    // Find by created user
    @GetMapping("/created-user/{createdUser}")
    public ResponseEntity<List<CategoryDTO>> findByCreatedUser(@PathVariable Long createdUser) {
        List<Category> categories = categoryService.findByCreatedUser(createdUser);
        return ResponseEntity.ok(convertToDTOList(categories));
    }

    // Find by updated user
    @GetMapping("/updated-user/{updatedUser}")
    public ResponseEntity<List<CategoryDTO>> findByUpdatedUser(@PathVariable Long updatedUser) {
        List<Category> categories = categoryService.findByUpdatedUser(updatedUser);
        return ResponseEntity.ok(convertToDTOList(categories));
    }

    // Convert Entity to DTO
    private CategoryDTO convertToDTO(Category category) {
        return CategoryDTO.builder().categoryId(category.getCategoryId()).categoryCode(category.getCategoryCode()).categoryName(category.getCategoryName()).status(category.getStatus()).createdUser(category.getCreatedUser()).updatedUser(category.getUpdatedUser()).build();
    }

    // Convert DTO to Entity
    private Category convertToEntity(CategoryDTO categoryDTO) {
        return Category.builder().categoryId(categoryDTO.getCategoryId()).categoryCode(categoryDTO.getCategoryCode()).categoryName(categoryDTO.getCategoryName()).status(categoryDTO.getStatus()).createdUser(categoryDTO.getCreatedUser()).updatedUser(categoryDTO.getUpdatedUser()).build();
    }

    // Convert List<Entity> to List<DTO>
    private List<CategoryDTO> convertToDTOList(List<Category> categories) {
        return categories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
