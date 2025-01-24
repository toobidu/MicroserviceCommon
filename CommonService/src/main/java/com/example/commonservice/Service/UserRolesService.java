package com.example.commonservice.Service;

import com.example.commonservice.Model.Entity.UserRoles;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRolesService {

    //Get all
    List<UserRoles> getAllUserRoles();

    //Create
    UserRoles createUserRoles(UserRoles userRoles);

    //Update
    Optional<UserRoles> updateUserRoles(Long userRolesId, UserRoles userRoles);

    //Delete
    boolean deleteUserRoles(Long userRolesId);

    //Search
    List<UserRoles> searchUserRoles(Long userRolesId, Long userId, Long roleId, Long createdUser, Long updatedUser, Date createdDate, Date updatedDate);
}
