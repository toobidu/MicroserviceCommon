package com.example.commonservice.Service.ServiceImplements;

import com.example.commonservice.Model.Entity.Department;
import com.example.commonservice.Repository.DepartmentRepository;
import com.example.commonservice.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
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
    @Transactional(readOnly = false)
    public Department createDepartment(Department department) {
        try {
            // Đảm bảo ID là null khi tạo mới
            department.setDepartmentId(null);

            // Set các giá trị mặc định nếu chưa có
            if (department.getStatus() == null) {
                department.setStatus(true);
            }

            // Lưu entity
            return departmentRepository.save(department);
        } catch (DataIntegrityViolationException e) {
            // Xử lý lỗi unique constraint (nếu có)
            throw new RuntimeException("Department code already exists", e);
        } catch (Exception e) {
            throw new RuntimeException("Error creating department", e);
        }
    }


    //Update
    @Override
    @Transactional(readOnly = false)
    public Optional<Department> updateDepartment(Department department, Long departmentId) {
        try{
            return departmentRepository.findById(departmentId)
                    .map(existingDepartment -> {

                        //Update
                        existingDepartment.setDepartmentCode(department.getDepartmentCode());
                        existingDepartment.setDepartmentName(department.getDepartmentName());
                        existingDepartment.setParentDepartmentId(department.getParentDepartmentId());
                        existingDepartment.setStatus(department.getStatus());
                        existingDepartment.setCreatedUser(department.getCreatedUser());
                        existingDepartment.setUpdatedUser(department.getUpdatedUser());
                        return departmentRepository.save(existingDepartment);
                    });
        }catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Department code already exists", e);
        } catch (Exception e) {
            throw new RuntimeException("Error updating department", e);
        }
    }

    //Delete
    @Override
    @Transactional(readOnly = false)
    public boolean deleteDepartment(Long departmentId) {
        try {
            if (departmentRepository.existsById(departmentId)) {
                departmentRepository.deleteById(departmentId);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting department", e);
        }
    }

    @Override
    public List<Department> searchDepartments(Long departmentId, String departmentCode, String departmentName, Long parentDepartmentId, Boolean status, Date createdTime, Date updatedTime, Long createdUser, Long updatedUser) {
        return departmentRepository.searchDepartmentsBy(departmentId, departmentCode, departmentName, parentDepartmentId, status, createdTime, updatedTime, createdUser, updatedUser);
    }
}
