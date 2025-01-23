package com.example.commonservice.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilesDTO {
    private Long fileId;
    private String fileName;
    private String filePath;
    private String businessCode;
    private Long businessId;
    private Boolean status;
    private Long createdUser;
    private Long updatedUser;
    private String createdTime;
    private String updatedTime;
}
