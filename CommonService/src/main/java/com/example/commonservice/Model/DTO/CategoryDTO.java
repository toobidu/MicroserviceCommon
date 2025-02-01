package com.example.commonservice.Model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {
    private Long categoryId;
    private String categoryCode;
    private String categoryName;
    private Boolean status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Long createdUser;
    private Long updatedUser;
}
