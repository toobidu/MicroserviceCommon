package com.example.commonservice.Controller;

import com.example.commonservice.Model.Entity.Department;
import com.example.commonservice.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/common/front-end/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //Get all
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    //Create
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department createdDepartment = departmentService.createDepartment(department);
        return ResponseEntity.ok(createdDepartment);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        Optional<Department> updatedDepartment = departmentService.updateDepartment(department, id);
        return updatedDepartment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        boolean deleted = departmentService.deleteDepartment(id);
        if (deleted) {
            return ResponseEntity.ok("Department deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error!");
        }
    }

    //Search
    @GetMapping("/search")
    public ResponseEntity<List<Department>> searchDepartment(
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) String departmentCode,
            @RequestParam(required = false) String departmentName,
            @RequestParam(required = false) Long parentDepartmentId,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date updatedTime,
            @RequestParam(required = false) Long createdUser,
            @RequestParam(required = false) Long updatedUser) {
        List<Department> departments = departmentService.searchDepartments(departmentId, departmentCode, departmentName, parentDepartmentId, status, createdTime, updatedTime, createdUser, updatedUser);
        return ResponseEntity.ok(departments);
    }
}
