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
    private Long userRoleId;

    @Column(name = "USER_ID")
    private Long userId;

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
    public UserRoles(Long userRoleId, Long userId, Long roleId, Long createdUser, Long updatedUser) {
        this.userRoleId = userRoleId;
        this.userId = userId;
        this.roleId = roleId;
        this.createdUser = createdUser;
        this.updatedUser = createdUser;
    }
}
