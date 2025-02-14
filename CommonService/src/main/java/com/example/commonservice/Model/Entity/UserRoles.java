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
@Table(name = "USER_ROLES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ROLES_SEQ")
    @SequenceGenerator(name = "USER_ROLES_SEQ", sequenceName = "USER_ROLES_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long userRolesId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "ROLE_ID")
    private Long roleId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @Column(name = "CREATED_USER")
    private Long createdUser;

    @Column(name = "UPDATED_USER")
    private Long updatedUser;

    //Constructor
    public UserRoles(Long userRolesId, Long userId, Long roleId, Long createdUser, Long updatedUser) {
        this.userRolesId = userRolesId;
        this.userId = userId;
        this.roleId = roleId;
        this.createdUser = createdUser;
        this.updatedUser = createdUser;
    }
}
