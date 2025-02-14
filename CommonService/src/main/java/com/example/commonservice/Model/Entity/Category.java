package com.example.commonservice.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "CATEGORY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
    @SequenceGenerator(name = "category_sequence", sequenceName = "CATEGORY_SEQ", allocationSize = 1)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "CATEGORY_CODE", length = 100, nullable = false)
    private String categoryCode;

    @Column(name = "CATEGORY_NAME", length = 500, nullable = false)
    private String categoryName;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "CREATED_TIME")
    @CreationTimestamp
    private LocalDateTime createdTime;

    @Column(name = "UPDATED_TIME")
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @Column(name = "CREATED_USER")
    private Long createdUser;

    @Column(name = "UPDATED_USER")
    private Long updatedUser;

    //Constructor
    public Category(Long categoryId, String categoryCode, String categoryName, Boolean status, Long createdUser, Long updatedUser) {
        this.categoryId = categoryId;
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.status = status;
        this.createdUser = createdUser;
        this.updatedUser = updatedUser;
    }
}
