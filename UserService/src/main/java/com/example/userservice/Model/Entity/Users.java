package com.example.userservice.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "USERS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    @SequenceGenerator(name = "users_sequence", sequenceName = "USERS_SEQ", allocationSize = 1)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USERNAME", length = 500, nullable = false, unique = true)
    private String userName;

    @Column(name = "EMAIL", length = 500, nullable = false)
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "PHONE_NUMBER", length = 50, nullable = false)
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String phoneNumber;

    @Column(name = "FIRST_NAME", length = 100, nullable = false)  // Fixed column name
    private String firstName;

    @Column(name = "LAST_NAME", length = 100, nullable = false)   // Fixed column name
    private String lastName;

    @Column(name = "DEPARTMENT_ID")
    private Long departmentId;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "CREATED_TIME")
    @CreationTimestamp
    private LocalDateTime createdTime;

    @Column(name = "UPDATED_TIME")
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @Column(name = "CREATED_USER")
    private Long createdUser;

    @Column(name = "UPDATED_USER")
    private Long updatedUser;

    @Column(name = "PASSWORD", length = 50, nullable = false)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10}$", message = "Password must contain at least 1 uppercase letter, 1 digit, and 1 special character")
    private String password;

    // Constructor with required fields
    public Users(Long userId, String userName, String email, String phoneNumber, String firstName, String lastName, String password, Boolean status, Long createdUser, Long updatedUser) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.status = status;
        this.createdUser = createdUser;
        this.updatedUser = updatedUser;
    }

}
