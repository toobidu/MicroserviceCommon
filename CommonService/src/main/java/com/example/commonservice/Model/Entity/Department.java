package com.example.commonservice.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "DEPARTMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "departmentId")
@ToString(of = {"departmentId", "departmentCode", "departmentName", "parentDepartmentId"})
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID")
    private Long departmentId;

    @Column(name = "DEPARTMENT_CODE", length = 100, nullable = false)
    private String departmentCode;

    @Column(name = "DEPARTMENT_NAME", length = 500, nullable = false)
    private String departmentName;

    @Column(name = "PARENT_DEPARTMENT_ID")
    private Long parentDepartmentId;

    @Column(name = "STATUS")
    private Boolean status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    @Setter(AccessLevel.NONE)
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TIME")
    @Setter(AccessLevel.NONE)
    private Date updatedTime;

    @Column(name = "CREATED_USER")
    private Long createdUser;

    @Column(name = "UPDATED_USER")
    private Long updatedUser;

    //Constructor

    public Department(
            Long departmentId,
            String departmentCode,
            String departmentName,
            Long parentDepartmentId,
            Boolean status,
            Long createdUser,
            Long updatedUser
    ) {
        this.departmentId = departmentId;
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.parentDepartmentId = parentDepartmentId;
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
