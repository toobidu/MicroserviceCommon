package com.example.commonservice.Service;

import com.example.commonservice.Model.Entity.ConfigView;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ConfigViewService {

    //Get all
    List<ConfigView> getAllConfigView();

    //Create new
    ConfigView createConfigView(ConfigView configView);

    //Update
    Optional<ConfigView> updateConfigView(Long configViewId, ConfigView configView);

    //Delete
    boolean deleteConfigView(Long configViewId);

    //Search
    List<ConfigView> searchConfigView(Long configViewId, String viewName, String viewPath, String apiPath, String roleId, Boolean status, Date createdTime, Date updatedTime, Long createdUser, Long updatedUser);
}
