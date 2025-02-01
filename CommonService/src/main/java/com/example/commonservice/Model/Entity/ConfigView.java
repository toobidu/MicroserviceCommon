package com.example.commonservice.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "CONFIG_VIEW")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigView {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "config_view_sequence")
    @SequenceGenerator(name = "config_view_sequence", sequenceName = "CONFIG_VIEW_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long configViewId;

    @NotBlank
    @Size(max = 500)
    @Column(name = "VIEW_NAME", length = 500, nullable = false)
    private String viewName;

    @NotBlank
    @Size(max = 500)
    @Column(name = "VIEW_PATH", length = 500, nullable = false)
    private String viewPath;

    @NotBlank
    @Size(max = 500)
    @Column(name = "API_PATH", length = 500, nullable = false)
    private String apiPath;

    @NotBlank
    @Size(max = 100)
    @Column(name = "ROLE_ID", length = 100, nullable = false)
    private String roleId;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;

    @Column(name = "UPDATED_TIME")
    private LocalDateTime updatedTime;

    @Column(name = "CREATED_USER")
    private Long createdUser;

    @Column(name = "UPDATED_USER")
    private Long updatedUser;

    @PrePersist
    protected void onCreate() {
        createdTime = LocalDateTime.now();
        updatedTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTime = LocalDateTime.now();
    }
}
