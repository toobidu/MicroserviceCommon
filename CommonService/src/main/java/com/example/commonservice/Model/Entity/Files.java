package com.example.commonservice.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "FILES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "files_sequence")
    @SequenceGenerator(name = "files_sequence", sequenceName = "FILES_SEQ", allocationSize = 1)
    @Column(name = "FILE_ID")
    private Long fileId;

    @Column(name = "FILE_NAME", length = 500, nullable = false)
    private String fileName;

    @Column(name = "FILE_PATH", length = 2000, nullable = false)
    private String filePath;

    @Column(name = "BUSINESS_CODE", length = 100, nullable = false)
    private String businessCode;

    @Column(name = "BUSINESS_ID")
    private Long businessId;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "CREATED_TIME")
    @CreationTimestamp
    private Date createdTime;

    @Column(name = "UPDATED_TIME")
    @UpdateTimestamp
    private Date updatedTime;

    @Column(name = "CREATED_USER")
    private Long createdUser;

    @Column(name = "UPDATED_USER")
    private Long updatedUser;

    //Constructor
    public Files(Long fileId, String fileName, String filePath, String businessCode, Long businessId, Boolean status, Long createdUser, Long updatedUser) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.businessCode = businessCode;
        this.businessId = businessId;
        this.status = status;
        this.createdUser = createdUser;
        this.updatedUser = updatedUser;
        this.createdTime = new Date();
        this.updatedTime = new Date();
    }
}
