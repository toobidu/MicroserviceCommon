package com.example.commonservice.Model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfigViewDTO {
    private Long configViewId;
    private String configViewName;
    private String configViewPath;
    private String apiPath;
    private Long roleId;
    private Boolean status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Long createdUser;
    private Long updatedUser;
}
