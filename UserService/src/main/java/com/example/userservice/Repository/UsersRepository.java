package com.example.userservice.Repository;

import com.example.userservice.Model.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByUserName(String userName);

    @Query("SELECT u FROM Users u WHERE "
            + "(:userId IS NULL OR u.userId = :userId) AND "
            + "(:userName IS NULL OR u.userName = :userName) AND "
            + "(:password IS NULL OR u.password = :password) AND "
            + "(:email IS NULL OR u.email = :email) AND "
            + "(:phoneNumber IS NULL OR u.phoneNumber = :phoneNumber) AND "
            + "(:firstName IS NULL OR u.firstName = :firstName) AND "
            + "(:lastName IS NULL OR u.lastName = :lastName) AND "
            + "(:departmentId IS NULL OR u.departmentId = :departmentId) AND "
            + "(:status IS NULL OR u.status = :status) AND "
            + "(:createdTime IS NULL OR u.createdTime = :createdTime) AND "
            + "(:updatedTime IS NULL OR u.updatedTime = :updatedTime) AND "
            + "(:createdUser IS NULL OR u.createdUser = :createdUser) AND "
            + "(:updatedUser IS NULL OR u.updatedUser = :updatedUser)")
    List<Users> searchUsersBy(
            @Param("userId") Long userId,
            @Param("userName") String userName,
            @Param("password") String password,
            @Param("email") String email,
            @Param("phoneNumber") String phoneNumber,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("departmentId") Long departmentId,
            @Param("status") Boolean status,
            @Param("createdUser") Long createdUser,
            @Param("updatedUser") Long updatedUser,
            @Param("createdTime") LocalDateTime createdTime,
            @Param("updatedTime") LocalDateTime updatedTime
    );
}
