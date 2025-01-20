package com.example.commonservice.Model.DTO;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigViewDTO {
    private Long configViewId;
    private String configViewName;
    private String configViewPath;
    private String apiPath;
    private Long roleId;
    private Boolean status;
    private Date createTime;
    private Date updateTime;
    private Long createdUser;
    private Long updatedUser;
}
