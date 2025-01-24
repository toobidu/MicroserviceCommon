package com.example.commonservice.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "USER_ROLES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userRoleId")
@ToString(of = {"userRoleId", "roleName"})
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ROLES_SEQ")
    @SequenceGenerator(name = "USER_ROLES_SEQ", sequenceName = "USER_ROLES_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long userRolesId;

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    private Users users;

    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "CREATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date createdTime;

    @Column(name = "UPDATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date updatedTime;

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
        this.createdTime = new Date();
        this.updatedTime = new Date();
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
