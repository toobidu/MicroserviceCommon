package com.example.commonservice.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "CATEGORY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "categoryId")
@ToString(of = {"categoryId", "categoryCode", "categoryName"})
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "CATEGORY_CODE", length = 100, nullable = false)
    private String categoryCode;

    @Column(name = "CATEGORY_NAME", length = 500, nullable = false)
    private String categoryName;

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
    public Category(
            String categoryCode,
            String categoryName,
            Boolean status,
            Long createdUser,
            Long updatedUser
    ) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.status = status;
        this.createdUser = createdUser;
        this.updatedUser = updatedUser;
        this.createdTime = new Date();  // Set createdTime to current time
        this.updatedTime = new Date();  // Set updatedTime to current time
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
