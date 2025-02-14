package com.example.userservice.Model.DTO;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UsersDTO {
    Long userId;

    @Size(min = 5, max = 500, message = "Username must be between 5 and 500 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must contain only letters and numbers")
    String userName;

    @Size(min = 10, max = 50, message = "Password must contain at least 1 uppercase letter, 1 digit, and 1 special character")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10}$", message = "Password must contain at least 1 uppercase letter, 1 digit, and 1 special character")
    String password;

    @Size(max = 500)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$",
            message = "Email must be a valid Gmail address (e.g., example@gmail.com)")
    String email;

    @Size(max = 50)
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must contain 10 digits")
    String phoneNumber;

    @Size(max = 100)
    String firstName;

    @Size(max = 100)
    String lastName;
    Long departmentId;
    Boolean status;
    Long createdUser;
    Long updatedUser;
    LocalDateTime createdTime;
    LocalDateTime updatedTime;

}
