package com.example.commonservice.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ItemDTO {
    private Long itemId;
    private String itemName;
    private String itemCode;
    private String itemValue;
    private Long parentItemId;
    private Long categoryId;
    private Boolean status;
    private Long createdUser;
    private Long updatedUser;
    private String createdTime;
    private String updatedTime;
}
