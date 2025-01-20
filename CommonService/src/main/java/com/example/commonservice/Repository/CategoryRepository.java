package com.example.commonservice.Repository;

import com.example.commonservice.Model.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Find by CategoryID
    Optional<Category> findByCategoryId(Long categoryId);

    // Find by CategoryCode
    Optional<Category> findByCategoryCode(String categoryCode);

    // Find by CategoryName
    Optional<Category> findByCategoryName(String categoryName);

    // Find by Status
    List<Category> findByStatus(Boolean status);

    // Find by CreatedTime
    List<Category> findByCreatedTime(Date createdTime);

    // Find by UpdatedTime
    List<Category> findByUpdatedTime(Date updatedTime);

    // Find by CreatedUser
    List<Category> findByCreatedUser(Long createdUser);

    // Find by UpdatedUser
    List<Category> findByUpdatedUser(Long updatedUser);
}
