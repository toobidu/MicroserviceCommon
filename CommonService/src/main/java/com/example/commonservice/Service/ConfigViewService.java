package com.example.commonservice.Service;


import com.example.commonservice.Model.Entity.ConfigView;

import java.util.*;
public interface ConfigViewService {

    //Get all
    List<ConfigView> getAllConfig();

    //Create new
    ConfigView createConfigView(ConfigView configView);

    //Update
    Optional<ConfigView> updateConfigView(Long configViewId, ConfigView configView);

    //Delete
    boolean deleteConfigView(Long configViewId);

    //Find by ID
    Optional<ConfigView> findById(Long configViewId);

    //Find by ViewName
    Optional<ConfigView> findByViewName(String viewName);

    //Find by ViewPath
    Optional<ConfigView> findByViewPath(String viewPath);

    //Find by ApiPath
    Optional<ConfigView> findByApiPath(String apiPath);

    //Find by RoleId
    List<ConfigView> findByRoleId(String roleId);

    //Find by Status
    List<ConfigView> findByStatus(boolean status);

    //Find by CreatedTime
    List<ConfigView> findByCreatedTime(Date createdTime);

    //Find by UpdatedTime
    List<ConfigView> findByUpdatedTime(Date updatedTime);

    //Find by CreatedUser
    List<ConfigView> findByCreatedUser(Long createdUser);

    //Find by UpdatedUser
    List<ConfigView> findByUpdatedUser(Long updatedUser);

}
