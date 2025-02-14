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
public class FilesDTO {
    Long fileId;

    @Size(max = 500)
    String fileName;

    @Size(max = 2000)
    String filePath;

    @Size(max = 100)
    String businessCode;
    Long businessId;
    Boolean status;
    Long createdUser;
    Long updatedUser;
    LocalDateTime createdTime;
    LocalDateTime updatedTime;
}
