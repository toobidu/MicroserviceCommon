package com.example.commonservice.Service.ServiceImplements;

import com.example.commonservice.Model.Entity.Department;
import com.example.commonservice.Repository.DepartmentRepository;
import com.example.commonservice.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImplement implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImplement(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    //Get all
    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    //Create
    @Override
    @Transactional
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    //Update
    @Override
    @Transactional
    public Optional<Department> updateDepartment(Department department, Long departmentId) {
        if (departmentRepository.existsById(departmentId)) {
            department.setDepartmentId(departmentId);
            return Optional.of(departmentRepository.save(department));
        }
        return Optional.empty();
    }

    //Delete
    @Override
    @Transactional
    public boolean deleteDepartment(Long departmentId) {
        if (departmentRepository.existsById(departmentId)) {
            departmentRepository.deleteById(departmentId);
            return true;
        }
        return false;
    }

    @Override
    public List<Department> searchDepartments(Long departmentId, String departmentCode, String departmentName, Long parentDepartmentId, Boolean status, Date createdTime, Date updatedTime, Long createdUser, Long updatedUser) {
        return departmentRepository.searchDepartmentsBy(departmentId, departmentCode, departmentName, parentDepartmentId, status, createdTime, updatedTime, createdUser, updatedUser);
    }
}
