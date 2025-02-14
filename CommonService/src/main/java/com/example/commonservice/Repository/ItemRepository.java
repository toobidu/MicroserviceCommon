package com.example.commonservice.Repository;

import com.example.commonservice.Model.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    // Custom query method for searching items by multiple criteria
    @Query("SELECT i FROM Item i WHERE "
            + "(:itemId IS NULL OR i.itemId = :itemId) AND "
            + "(:itemCode IS NULL OR i.itemCode = :itemCode) AND "
            + "(:itemName IS NULL OR i.itemName = :itemName) AND "
            + "(:itemValue IS NULL OR i.itemValue = :itemValue) AND "
            + "(:parentItemId IS NULL OR i.parentItemId = :parentItemId) AND "
            + "(:categoryId IS NULL OR i.categoryId = :categoryId) AND "
            + "(:status IS NULL OR i.status = :status) AND "
            + "(:createdTime IS NULL OR i.createdTime = :createdTime) AND "
            + "(:updatedTime IS NULL OR i.updatedTime = :updatedTime) AND "
            + "(:createdUser IS NULL OR i.createdUser = :createdUser) AND "
            + "(:updatedUser IS NULL OR i.updatedUser = :updatedUser)")
    List<Item> searchItemsBy(
            @Param("itemId") Long itemId,
            @Param("itemCode") String itemCode,
            @Param("itemName") String itemName,
            @Param("itemValue") String itemValue,
            @Param("parentItemId") Long parentItemId,
            @Param("categoryId") Long categoryId,
            @Param("status") Boolean status,
            @Param("createdTime") LocalDateTime createdTime,
            @Param("updatedTime") LocalDateTime updatedTime,
            @Param("createdUser") Long createdUser,
            @Param("updatedUser") Long updatedUser);
}
