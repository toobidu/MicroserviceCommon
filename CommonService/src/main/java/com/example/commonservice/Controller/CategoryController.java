package com.example.commonservice.Controller;

import com.example.commonservice.Model.DTO.CategoryDTO;
import com.example.commonservice.Model.Entity.Category;
import com.example.commonservice.Model.Response.ApiResponse;
import com.example.commonservice.Service.CategoryService;
import com.example.commonservice.Utility.ConvertDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/common/front-end/category")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Validated
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAllCategory() {
        try {
            List<Category> categories = categoryService.getAllCategory();
            List<CategoryDTO> dtos = ConvertDTO.convertToDTOList(categories, CategoryDTO.class);
            return ResponseEntity.ok(ApiResponse.success(dtos));
        } catch (Exception e) {
            log.error("Error getting all categories: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Failed to retrieve categories"));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDTO>> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        try {
            Category category = ConvertDTO.convertToEntity(categoryDTO, Category.class);
            Category createdCategory = categoryService.createCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(ConvertDTO.convertToDTO(createdCategory, CategoryDTO.class)));
        } catch (Exception e) {
            log.error("Error creating category: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Failed to create category"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
        try {
            Category category = ConvertDTO.convertToEntity(categoryDTO, Category.class);
            return categoryService.updateCategory(id, category).map(updated -> ResponseEntity.ok(ApiResponse.success(ConvertDTO.convertToDTO(updated, CategoryDTO.class)))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Category not found")));
        } catch (Exception e) {
            log.error("Error updating category: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Failed to update category"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {
        try {
            boolean isDeleted = categoryService.deleteCategory(id);
            if (isDeleted) {
                return ResponseEntity.ok(ApiResponse.success(null, "Category deleted successfully"));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Category not found"));
        } catch (Exception e) {
            log.error("Error deleting category: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Failed to delete category"));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> searchCategories(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) String categoryCode, @RequestParam(required = false) String categoryName, @RequestParam(required = false) Boolean status, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdTime, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date updatedTime, @RequestParam(required = false) Long createdUser, @RequestParam(required = false) Long updatedUser) {

        try {
            List<Category> categories;

            if (categoryId != null) {
                return categoryService.findByCategoryId(categoryId).map(category -> ResponseEntity.ok(ApiResponse.success(List.of(ConvertDTO.convertToDTO(category, CategoryDTO.class))))).orElse(ResponseEntity.ok(ApiResponse.success(Collections.emptyList())));
            }

            if (categoryCode != null) {
                return categoryService.findByCategoryCode(categoryCode).map(category -> ResponseEntity.ok(ApiResponse.success(List.of(ConvertDTO.convertToDTO(category, CategoryDTO.class))))).orElse(ResponseEntity.ok(ApiResponse.success(Collections.emptyList())));
            }

            if (categoryName != null) {
                return categoryService.findByCategoryName(categoryName).map(category -> ResponseEntity.ok(ApiResponse.success(List.of(ConvertDTO.convertToDTO(category, CategoryDTO.class))))).orElse(ResponseEntity.ok(ApiResponse.success(Collections.emptyList())));
            }

            if (status != null) {
                categories = categoryService.findByStatus(status);
            } else if (createdTime != null) {
                categories = categoryService.findByCreatedTime(createdTime);
            } else if (updatedTime != null) {
                categories = categoryService.findByUpdatedTime(updatedTime);
            } else if (createdUser != null) {
                categories = categoryService.findByCreatedUser(createdUser);
            } else if (updatedUser != null) {
                categories = categoryService.findByUpdatedUser(updatedUser);
            } else {
                categories = Collections.emptyList();
            }

            return ResponseEntity.ok(ApiResponse.success(ConvertDTO.convertToDTOList(categories, CategoryDTO.class)));
        } catch (Exception e) {
            log.error("Error searching categories: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Failed to search categories"));
        }
    }
}
