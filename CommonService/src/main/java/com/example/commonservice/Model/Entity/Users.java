package com.example.commonservice.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userId")
@ToString(of = {"userId", "userName", "email", "phoneNumber", "firstName", "lastName", "departmentId"})
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "USERS_SEQ",
            allocationSize = 1
    )
    @Column(name = "USER_ID")
    private Long userId;






}
