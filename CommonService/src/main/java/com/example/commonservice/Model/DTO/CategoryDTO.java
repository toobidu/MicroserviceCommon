package com.example.commonservice.Model.DTO;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    private Long categoryId;
    private String categoryCode;
    private String categoryName;
    private Boolean status;
    private Date createTime;
    private Date updateTime;
    private Long createdUser;
    private Long updatedUser;
}
