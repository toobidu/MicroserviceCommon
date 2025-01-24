package com.example.commonservice.Controller;

import com.example.commonservice.Model.Entity.UserRoles;
import com.example.commonservice.Service.UserRolesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/common/front-end/user-roles")
public class UserRolesController {

    private final UserRolesService userRolesService;

    @Autowired
    public UserRolesController(UserRolesService userRolesService) {
        this.userRolesService = userRolesService;
    }

    //Get all user roles
    @GetMapping
    public ResponseEntity<List<UserRoles>> getAllUserRoles() {
        List<UserRoles> userRoles = userRolesService.getAllUserRoles();
        return ResponseEntity.ok(userRoles);
    }

    //Create a new user role
    @PostMapping
    public ResponseEntity<UserRoles> createUserRole(@RequestBody @Valid UserRoles userRoles) {
        UserRoles createdUserRole = userRolesService.createUserRoles(userRoles);
        return ResponseEntity.ok(createdUserRole);
    }

    //Update a user role
    @PutMapping("/{id}")
    public ResponseEntity<UserRoles> updateUserRole(@PathVariable Long id, @RequestBody @Valid UserRoles userRoles) {
        Optional<UserRoles> updatedUserRole = userRolesService.updateUserRoles(id, userRoles);
        return updatedUserRole.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Delete a user role
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserRole(@PathVariable Long id) {
        boolean isDeleted = userRolesService.deleteUserRoles(id);
        if (isDeleted) {
            return ResponseEntity.ok("User role is deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error!");
        }
    }

    //Search user roles by multiple criteria
    @GetMapping("/search")
    public ResponseEntity<List<UserRoles>> searchUserRoles(
            @RequestParam(required = false) Long userRolesId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long roleId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date updatedTime,
            @RequestParam(required = false) Long createdUser,
            @RequestParam(required = false) Long updatedUser) {

        List<UserRoles> userRoles = userRolesService.searchUserRoles(userRolesId, userId, roleId, createdUser, updatedUser, createdTime, updatedTime);
        return ResponseEntity.ok(userRoles);
    }
}
