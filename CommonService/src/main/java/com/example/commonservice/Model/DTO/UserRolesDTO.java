package com.example.commonservice.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRolesDTO {

    private Long userRolesId;
    private Long userId;
    private Long roleId;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Long createdUser;
    private Long updatedUser;

}
