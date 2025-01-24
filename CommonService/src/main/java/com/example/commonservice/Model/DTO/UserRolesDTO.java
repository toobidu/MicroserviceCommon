package com.example.commonservice.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRolesDTO {

    private Long userRolesId;
    private Long userId;
    private Long roleId;
    private Date createdTime;
    private Date updatedTime;
    private Long createdUser;
    private Long updatedUser;

}
