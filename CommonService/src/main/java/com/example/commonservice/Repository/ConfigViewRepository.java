package com.example.commonservice.Repository;

import com.example.commonservice.Model.Entity.ConfigView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface ConfigViewRepository extends JpaRepository<ConfigView, Long> {

    //Find by ConfigViewId
    Optional<ConfigView> findByConfigViewId(Long configViewId);

    //Find by ViewName
    Optional<ConfigView> findByViewName(String viewName);

    //Find by ViewPath
    Optional<ConfigView> findByViewPath(String ViewPath);

    //Find by ApiPath
    Optional<ConfigView> findByApiPath(String apiPath);

    //Find by RoleId
    List<ConfigView> findByRoleId(String roleId);

    // Find by Status
    List<ConfigView> findByStatus(Boolean status);

    // Find by CreatedTime
    List<ConfigView> findByCreatedTime(Date createdTime);

    // Find by UpdatedTime
    List<ConfigView> findByUpdatedTime(Date updatedTime);

    // Find by CreatedUser
    List<ConfigView> findByCreatedUser(Long createdUser);

    // Find by UpdatedUser
    List<ConfigView> findByUpdatedUser(Long updatedUser);
}
