package com.example.commonservice.Repository;

import com.example.commonservice.Model.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT c FROM Department c WHERE "
            + "(:departmentId IS NULL OR c.departmentId = :departmentId) AND "
            + "(:departmentCode IS NULL OR c.departmentCode = :departmentCode) AND "
            + "(:departmentName IS NULL OR c.departmentName = :departmentName) AND "
            + "(:parentDepartmentId IS NULL OR c.parentDepartmentId = :parentDepartmentId) AND"
            + "(:status IS NULL OR c.status = :status) AND "
            + "(:createdTime IS NULL OR c.createdTime = :createdTime) AND "
            + "(:updatedTime IS NULL OR c.updatedTime = :updatedTime) AND "
            + "(:createdUser IS NULL OR c.createdUser = :createdUser) AND "
            + "(:updatedUser IS NULL OR c.updatedUser = :updatedUser)")
    List<Department> searchDepartmentsBy(
            @Param("departmentId") Long departmentId,
            @Param("departmentCode") String departmentCode,
            @Param("departmentName") String departmentName,
            @Param("parentDepartmentId") Long parentDepartmentId,
            @Param("status") Boolean status,
            @Param("createdTime") LocalDateTime createdTime,
            @Param("updatedTime") LocalDateTime updatedTime,
            @Param("createdUser") Long createdUser,
            @Param("updatedUser") Long updatedUser);

}
