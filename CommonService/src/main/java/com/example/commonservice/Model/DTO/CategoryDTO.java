package com.example.commonservice.Model.DTO;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CategoryDTO {
    Long categoryId;

    @Size(max = 100)
    String categoryCode;

    @Size(max = 500)
    String categoryName;

    Boolean status;
    LocalDateTime createdTime;
    LocalDateTime updatedTime;
    Long createdUser;
    Long updatedUser;

}
