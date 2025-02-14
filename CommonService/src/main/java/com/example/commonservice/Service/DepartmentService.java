package com.example.commonservice.Service;

import com.example.commonservice.Model.Entity.Department;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    //Get all
    List<Department> getAllDepartments();

    //Create
    Department createDepartment(Department department);

    //Update
    Optional<Department> updateDepartment(Department department, Long departmentId);

    //Delete
    boolean deleteDepartment(Long departmentId);

    //Search
    List<Department> searchDepartments(Long departmentId, String departmentCode, String departmentName, Long parentDepartmentId, Boolean status, LocalDateTime createdTime, LocalDateTime updatedTime, Long createdUser, Long updatedUser);
}
