package com.example.commonservice.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Table(name = "CONFIG_VIEW")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "configViewId")
@ToString(of = {"configViewId", "viewName", "viewPath", "apiPath", "roleId"})
@Builder
public class ConfigView {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "config_view_sequence")
    @SequenceGenerator(
            name = "config_view_sequence",
            sequenceName = "CONFIG_VIEW_SEQ",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long configViewId;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @Column(name = "VIEW_NAME", length = 500, nullable = false)
    private String viewName;

    @Column(name = "VIEW_PATH", length = 500, nullable = false)
    private String viewPath;

    @Column(name = "API_PATH", length = 500, nullable = false)
    private String apiPath;

    @Column(name = "ROLE_ID", length = 100, nullable = false)
    private String roleId;

    @Column(name = "STATUS")
    private Boolean status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TIME")
    private Date updatedTime;

    @Column(name = "CREATED_USER")
    private Long createdUser;

    @Column(name = "UPDATED_USER")
    private Long updatedUser;

    //Constructor
    public ConfigView(
            Long configViewId,
            String viewName,
            String viewPath,
            String apiPath,
            String roleId,
            Boolean status,
            Long createdUser,
            Long updatedUser) {
        this.configViewId = configViewId;
        this.viewName = viewName;
        this.viewPath = viewPath;
        this.apiPath = apiPath;
        this.roleId = roleId;
        this.status = status;
        this.createdTime = new Date();
        this.updatedTime = new Date();
        this.createdUser = createdUser;
        this.updatedUser = updatedUser;
    }

    @PrePersist
    protected void toCreate() {
        this.createdTime = new Date();
        this.updatedTime = new Date();
    }

    @PreUpdate
    protected void toUpdate() {
        this.updatedTime = new Date();
    }
}
