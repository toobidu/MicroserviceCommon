package com.example.commonservice.Service.ServiceImplements;

import com.example.commonservice.Model.Entity.UserRoles;
import com.example.commonservice.Repository.UserRolesRepository;
import com.example.commonservice.Service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserRolesServiceImplement implements UserRolesService {

    private final UserRolesRepository userRolesRepository;

    @Autowired
    public UserRolesServiceImplement(UserRolesRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    //Get all user roles
    @Override
    public List<UserRoles> getAllUserRoles() {
        return userRolesRepository.findAll();
    }

    //Create
    @Override
    @Transactional(readOnly = false)
    public UserRoles createUserRoles(UserRoles userRoles) {
        try {
            userRoles.setUserRolesId(null);
            return userRolesRepository.save(userRoles);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("User role already exists", e);
        } catch (Exception e) {
            throw new RuntimeException("Error creating user role", e);
        }
    }

    //Update
    @Override
    @Transactional(readOnly = false)
    public Optional<UserRoles> updateUserRoles(Long userRolesId, UserRoles userRoles) {
        try {
            return userRolesRepository.findById(userRolesId)
                    .map(existingUserRoles -> {
                        existingUserRoles.setUserId(userRoles.getUserId());
                        existingUserRoles.setRoleId(userRoles.getRoleId());
                        existingUserRoles.setCreatedUser(userRoles.getCreatedUser());
                        existingUserRoles.setUpdatedUser(userRoles.getUpdatedUser());
                        return userRolesRepository.save(existingUserRoles);
                    });
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("User Roles code already exists", e);
        } catch (Exception e) {
            throw new RuntimeException("Error updating user roles", e);
        }
    }

    //Delete
    @Override
    @Transactional(readOnly = false)
    public boolean deleteUserRoles(Long userRolesId) {
        try {
            Optional<UserRoles> userRolesOptional = userRolesRepository.findById(userRolesId);
            if (userRolesOptional.isPresent()) {
                userRolesRepository.deleteById(userRolesId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user roles", e);
        }
    }

    //Search user roles by multiple criteria

    @Override
    public List<UserRoles> searchUserRoles(Long userRolesId, Long userId, Long roleId, Long createdUser, Long updatedUser, LocalDateTime createdDate, LocalDateTime updatedDate) {
        return userRolesRepository.searchUserRolesBy(userRolesId, userId, roleId, createdDate, updatedDate, createdUser, updatedUser);
    }
}
