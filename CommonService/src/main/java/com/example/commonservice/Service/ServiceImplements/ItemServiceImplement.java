package com.example.commonservice.Service.ServiceImplements;

import com.example.commonservice.Model.Entity.Item;
import com.example.commonservice.Repository.ItemRepository;
import com.example.commonservice.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ItemServiceImplement implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImplement(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Get all items
    @Override
    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    // Create a new item
    @Override
    @Transactional(readOnly = false)
    public Item createItem(Item item) {
        try {
            // Ensure ID is null when creating new
            item.setItemId(null);
            if (item.getStatus() == null) {
                item.setStatus(true);
            }

            // Save entity
            return itemRepository.save(item);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Item code already exists", e);
        } catch (Exception e) {
                throw new RuntimeException("Error creating item", e);
        }
    }

    // Update an item
    @Override
    @Transactional(readOnly = false)
    public Optional<Item> updateItem(Long itemId, Item item) {
        try {
            return itemRepository.findById(itemId)
                    .map(existingItem -> {
                        // Cập nhật từng trường một thay vì save trực tiếp
                        existingItem.setItemCode(item.getItemCode());
                        existingItem.setItemName(item.getItemName());
                        existingItem.setItemValue(item.getItemValue());
                        existingItem.setParentItemId(item.getParentItemId());
                        existingItem.setCategoryId(item.getCategoryId());
                        existingItem.setStatus(item.getStatus());
                        existingItem.setCreatedUser(item.getCreatedUser());
                        existingItem.setUpdatedUser(item.getUpdatedUser());
                        return itemRepository.save(existingItem);
                    });
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Item code already exists", e);
        } catch (Exception e) {
            throw new RuntimeException("Error updating item", e);
        }
    }

    // Delete an item
    @Override
    @Transactional(readOnly = false)
    public boolean deleteItem(Long itemId) {
        try {
            if (itemRepository.existsById(itemId)) {
                itemRepository.deleteById(itemId);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting item", e);
        }
    }

    // Search items by multiple criteria
    @Override
    public List<Item> searchItems(Long itemId, String itemCode, String itemName, String itemValue, Long parentItemId, Long categoryId, Boolean status, Date createdTime, Date updatedTime, Long createdUser, Long updatedUser) {
        // Implement the search logic based on the criteria
        return itemRepository.searchItemsBy(itemId, itemCode, itemName, itemValue, parentItemId, categoryId, status, createdTime, updatedTime, createdUser, updatedUser);
    }
}
