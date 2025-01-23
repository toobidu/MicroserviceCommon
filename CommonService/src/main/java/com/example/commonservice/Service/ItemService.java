package com.example.commonservice.Service;

import com.example.commonservice.Model.Entity.Item;

import java.util.*;

public interface ItemService {

    // Get all items
    List<Item> getAllItem();

    // Create a new item
    Item createItem(Item item);

    // Update an item
    Optional<Item> updateItem(Long itemId, Item item);

    // Delete an item
    boolean deleteItem(Long itemId);

    // Search items by multiple criteria
    List<Item> searchItems(Long itemId, String itemCode, String itemName, String itemValue, Long parentItemId, Long categoryId, Boolean status, Date createdTime, Date updatedTime, Long createdUser, Long updatedUser);
}
