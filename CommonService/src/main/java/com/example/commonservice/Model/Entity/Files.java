package com.example.commonservice.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "FILES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "fileId")
@ToString(of = {"fileId", "fileName", "filePath", "businessCode", "businessId"})
@Builder
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public Files(
            Long fileId,
            String fileName,
            String filePath,
            String businessCode,
            Long businessId,
            Boolean status,
            Long createdUser,
            Long updatedUser
    ){
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

    @PrePersist
    protected void toCreate(){
        this.createdTime = new Date();
        this.updatedTime = new Date();
    }

    @PreUpdate
    protected void toUpdate(){
        this.updatedTime = new Date();
    }
}
