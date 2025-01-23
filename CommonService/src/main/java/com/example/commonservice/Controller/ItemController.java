package com.example.commonservice.Controller;

import com.example.commonservice.Model.Entity.Item;
import com.example.commonservice.Service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/common/front-end/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // Get all items
    @GetMapping
    public ResponseEntity<List<Item>> getAllItem() {
        List<Item> items = itemService.getAllItem();
        return ResponseEntity.ok(items);
    }

    //Create a new item
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody @Valid Item item) {
        Item createdItem = itemService.createItem(item);
        return ResponseEntity.ok(createdItem);
    }

    //Update an item
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long itemId, @RequestBody @Valid Item item) {
        return itemService.updateItem(itemId, item)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Delete an item
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long itemId) {
        boolean isDeleted = itemService.deleteItem(itemId);
        if (isDeleted) {
            return ResponseEntity.ok("Item is deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error!");
        }
    }

    //Search items by multiple criteria
    @GetMapping("/search")
    public ResponseEntity<List<Item>> searchItems(
            @RequestParam(required = false) Long itemId,
            @RequestParam(required = false) String itemCode,
            @RequestParam(required = false) String itemName,
            @RequestParam(required = false) String itemValue,
            @RequestParam(required = false) Long parentItemId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date updatedTime,
            @RequestParam(required = false) Long createdUser,
            @RequestParam(required = false) Long updatedUser) {

        List<Item> items = itemService.searchItems(itemId, itemCode, itemName, itemValue, parentItemId, categoryId, status, createdTime, updatedTime, createdUser, updatedUser);
        return ResponseEntity.ok(items);
    }
}
