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
public class ConfigViewDTO {
    Long configViewId;

    @Size(max = 500)
    String viewName;

    @Size(max = 500)
    String viewPath;

    @Size(max = 500)
    String apiPath;

    @Size(max = 100)
    String roleId;
    Boolean status;
    LocalDateTime createdTime;
    LocalDateTime updatedTime;
    Long createdUser;
    Long updatedUser;
}
