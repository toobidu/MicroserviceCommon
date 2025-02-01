package com.example.commonservice.Model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentDTO {
    private Long departmentId;
    private String departmentCode;
    private String departmentName;
    private Long parentDepartmentId;
    private Boolean status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Long createdUser;
    private Long updatedUser;
}
