package com.example.userservice.Service.Implement;

import com.example.userservice.Model.DTO.UsersDTO;
import com.example.userservice.Model.Entity.Users;
import com.example.userservice.Repository.UsersRepository;
import com.example.userservice.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UsersServiceImplement implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    private UsersDTO convertToDTO(Users user) {
        UsersDTO dto = new UsersDTO();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setDepartmentId(user.getDepartmentId());
        dto.setStatus(user.getStatus());
        dto.setCreatedUser(user.getCreatedUser());
        dto.setUpdatedUser(user.getUpdatedUser());
        dto.setCreatedTime(user.getCreatedTime());
        dto.setUpdatedTime(user.getUpdatedTime());
        return dto;
    }

    private Users convertToEntity(UsersDTO dto) {
        Users user = new Users();
        user.setUserId(dto.getUserId());
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setDepartmentId(dto.getDepartmentId());
        user.setStatus(dto.getStatus());
        user.setCreatedUser(dto.getCreatedUser());
        user.setUpdatedUser(dto.getUpdatedUser());
        user.setCreatedTime(dto.getCreatedTime());
        user.setUpdatedTime(dto.getUpdatedTime());
        return user;
    }

    @Override
    public List<UsersDTO> getAllUsers() {
        return usersRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UsersDTO createUser(UsersDTO userDTO) {
        if (usersRepository.existsByUserName(userDTO.getUserName())) {
            throw new RuntimeException("Username already exists");
        }
        try {
            userDTO.setUserId(null);
            if (userDTO.getStatus() == null) {
                userDTO.setStatus(true);
            }
            Users savedUser = usersRepository.save(convertToEntity(userDTO));
            return convertToDTO(savedUser);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("User code already exists", e);
        } catch (Exception e) {
            throw new RuntimeException("Error creating user", e);
        }
    }

    @Override
    @Transactional
    public Optional<UsersDTO> updateUser(Long userId, UsersDTO userDTO) {
        try {
            return usersRepository.findById(userId)
                    .map(existingUser -> {
                        Users updatedUser = convertToEntity(userDTO);
                        updatedUser.setUserId(userId);
                        return convertToDTO(usersRepository.save(updatedUser));
                    });
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("User code already exists", e);
        } catch (Exception e) {
            throw new RuntimeException("Error updating user", e);
        }
    }

    @Override
    @Transactional
    public boolean deleteUser(Long userId) {
        try {
            if (usersRepository.existsById(userId)) {
                usersRepository.deleteById(userId);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }

    @Override
    public List<UsersDTO> searchUsers(UsersDTO searchCriteria) {
        List<Users> users = usersRepository.searchUsersBy(
                searchCriteria.getUserId(),
                searchCriteria.getUserName(),
                searchCriteria.getPassword(),
                searchCriteria.getEmail(),
                searchCriteria.getPhoneNumber(),
                searchCriteria.getFirstName(),
                searchCriteria.getLastName(),
                searchCriteria.getDepartmentId(),
                searchCriteria.getStatus(),
                searchCriteria.getCreatedUser(),
                searchCriteria.getUpdatedUser(),
                searchCriteria.getCreatedTime(),
                searchCriteria.getUpdatedTime()
        );
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
