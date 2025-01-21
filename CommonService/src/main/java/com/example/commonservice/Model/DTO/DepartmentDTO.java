package com.example.commonservice.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDTO {
    private Long departmentId;
    private String departmentCode;
    private String departmentName;
    private Long parentDepartmentId;
    private Boolean status;
    private Date createTime;
    private Date updateTime;
    private Long createdUser;
    private Long updatedUser;
}
