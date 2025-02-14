package com.example.userservice.Service;

import com.example.userservice.Model.DTO.UsersDTO;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<UsersDTO> getAllUsers();

    UsersDTO createUser(UsersDTO userDTO);

    Optional<UsersDTO> updateUser(Long userId, UsersDTO userDTO);

    boolean deleteUser(Long userId);

    List<UsersDTO> searchUsers(UsersDTO searchCriteria);
}
