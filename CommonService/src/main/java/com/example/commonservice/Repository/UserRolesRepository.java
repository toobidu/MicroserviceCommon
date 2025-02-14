package com.example.commonservice.Repository;

import com.example.commonservice.Model.Entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    // Custom query method for searching user roles by multiple criteria
    @Query("SELECT ur FROM UserRoles ur WHERE "
            + "(:userRolesId IS NULL OR ur.userRolesId = :userRolesId) AND "
            + "(:userId IS NULL OR ur.userId = :userId) AND "
            + "(:roleId IS NULL OR ur.roleId = :roleId) AND "
            + "(:createdTime IS NULL OR ur.createdTime = :createdTime) AND "
            + "(:updatedTime IS NULL OR ur.updatedTime = :updatedTime) AND "
            + "(:createdUser IS NULL OR ur.createdUser = :createdUser) AND "
            + "(:updatedUser IS NULL OR ur.updatedUser = :updatedUser)")
    List<UserRoles> searchUserRolesBy(
            @Param("userRoleId") Long userRoleId,
            @Param("userId") Long userId,
            @Param("roleId") Long roleId,
            @Param("createdTime") LocalDateTime createdTime,
            @Param("updatedTime") LocalDateTime updatedTime,
            @Param("createdUser") Long createdUser,
            @Param("updatedUser") Long updatedUser

    );

}
