package com.example.commonservice.Model.DTO;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDTO {
    Long itemId;

    @Size(max = 500)
    String itemName;

    @Size(max = 100)
    String itemCode;

    @Size(max = 500)
    String itemValue;
    Long parentItemId;
    Long categoryId;
    Boolean status;
    LocalDateTime createdTime;
    LocalDateTime updatedTime;
    Long createdUser;
    Long updatedUser;
}
