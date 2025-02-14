package com.example.commonservice.Repository;

import com.example.commonservice.Model.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Custom query method for searching categories by multiple criteria
    @Query("SELECT c FROM Category c WHERE "
            + "(:categoryId IS NULL OR c.categoryId = :categoryId) AND "
            + "(:categoryCode IS NULL OR c.categoryCode = :categoryCode) AND "
            + "(:categoryName IS NULL OR c.categoryName = :categoryName) AND "
            + "(:status IS NULL OR c.status = :status) AND "
            + "(:createdTime IS NULL OR c.createdTime = :createdTime) AND "
            + "(:updatedTime IS NULL OR c.updatedTime = :updatedTime) AND "
            + "(:createdUser IS NULL OR c.createdUser = :createdUser) AND "
            + "(:updatedUser IS NULL OR c.updatedUser = :updatedUser)")
    List<Category> searchCategoriesBy(
            @Param("categoryId") Long categoryId,
            @Param("categoryCode") String categoryCode,
            @Param("categoryName") String categoryName,
            @Param("status") Boolean status,
            @Param("createdTime") LocalDateTime createdTime,
            @Param("updatedTime") LocalDateTime updatedTime,
            @Param("createdUser") Long createdUser,
            @Param("updatedUser") Long updatedUser);

}
