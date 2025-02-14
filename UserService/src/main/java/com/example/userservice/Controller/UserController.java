package com.example.userservice.Controller;

import com.example.userservice.Model.DTO.UsersDTO;
import com.example.userservice.Model.Response.ApiResponse;
import com.example.userservice.Service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/common/front-end/users")
public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsersDTO>>> getAllUsers() {
        ApiResponse<List<UsersDTO>> response = new ApiResponse<>();
        try {
            List<UsersDTO> users = usersService.getAllUsers();
            response.setData(users);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setCode(1001);
            response.setMessage("Error getting users: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UsersDTO>> createUser(@RequestBody @Valid UsersDTO userDTO) {
        ApiResponse<UsersDTO> response = new ApiResponse<>();
        try {
            UsersDTO createdUser = usersService.createUser(userDTO);
            response.setData(createdUser);
            response.setMessage("User created successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(1002);
            response.setMessage("Error creating user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response.setCode(1001);
            response.setMessage("Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsersDTO>> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UsersDTO userDTO) {
        ApiResponse<UsersDTO> response = new ApiResponse<>();
        try {
            Optional<UsersDTO> updatedUser = usersService.updateUser(id, userDTO);
            if (updatedUser.isPresent()) {
                response.setData(updatedUser.get());
                response.setMessage("User updated successfully");
                return ResponseEntity.ok(response);
            } else {
                response.setCode(1003);
                response.setMessage("User not found with id: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.setCode(1001);
            response.setMessage("Error updating user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        ApiResponse<Void> response = new ApiResponse<>();
        try {
            boolean isDeleted = usersService.deleteUser(id);
            if (isDeleted) {
                response.setMessage("User deleted successfully");
                return ResponseEntity.ok(response);
            } else {
                response.setCode(1003);
                response.setMessage("User not found with id: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.setCode(1001);
            response.setMessage("Error deleting user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<UsersDTO>>> searchUsers(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) Long createdUser,
            @RequestParam(required = false) Long updatedUser,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime createdTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime updatedTime) {

        ApiResponse<List<UsersDTO>> response = new ApiResponse<>();
        try {
            UsersDTO searchCriteria = new UsersDTO();
            searchCriteria.setUserId(userId);
            searchCriteria.setUserName(userName);
            searchCriteria.setPassword(password);
            searchCriteria.setEmail(email);
            searchCriteria.setPhoneNumber(phoneNumber);
            searchCriteria.setFirstName(firstName);
            searchCriteria.setLastName(lastName);
            searchCriteria.setDepartmentId(departmentId);
            searchCriteria.setStatus(status);
            searchCriteria.setCreatedUser(createdUser);
            searchCriteria.setUpdatedUser(updatedUser);
            searchCriteria.setCreatedTime(createdTime);
            searchCriteria.setUpdatedTime(updatedTime);

            List<UsersDTO> users = usersService.searchUsers(searchCriteria);
            response.setData(users);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setCode(1001);
            response.setMessage("Error searching users: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
