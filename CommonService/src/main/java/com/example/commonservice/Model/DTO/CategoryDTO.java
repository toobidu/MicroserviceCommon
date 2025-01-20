package com.example.commonservice.Model.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    private Long categoryId;
    private String categoryCode;
    private String categoryName;
    private Boolean status;
    private Long createdUser;
    private Long updatedUser;
}
