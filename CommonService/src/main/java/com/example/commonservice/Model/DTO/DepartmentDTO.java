package com.example.commonservice.Model.DTO;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentDTO {
    Long departmentId;

    @Size(max = 100)
    String departmentCode;

    @Size(max = 500)
    String departmentName;
    Long parentDepartmentId;
    Boolean status;
    LocalDateTime createdTime;
    LocalDateTime updatedTime;
    Long createdUser;
    Long updatedUser;
}
