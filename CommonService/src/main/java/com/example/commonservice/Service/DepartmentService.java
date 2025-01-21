package com.example.commonservice.Service;

import com.example.commonservice.Model.Entity.Department;

import java.util.Date;
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
    List<Department> searchDepartments(Long departmentId, String departmentCode, String departmentName, Long parentDepartmentId, Boolean status, Date createdTime, Date updatedTime, Long createdUser, Long updatedUser);
}
