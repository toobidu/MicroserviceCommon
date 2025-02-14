package com.example.commonservice.Repository;


import com.example.commonservice.Model.Entity.ConfigView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConfigViewRepository extends JpaRepository<ConfigView, Long> {

    @Query("SELECT c FROM ConfigView c WHERE "
            + "(:configViewId IS NULL OR c.configViewId = :configViewId) AND "
            + "(:viewName IS NULL OR c.viewName = :viewName) AND "
            + "(:viewPath IS NULL OR c.viewPath = :viewPath) AND "
            + "(:apiPath IS NULL OR c.apiPath = :apiPath) AND"
            + "(:roleId IS NULL OR c.roleId= :roleId) AND "
            + "(:status IS NULL OR c.status = :status) AND "
            + "(:createdTime IS NULL OR c.createdTime = :createdTime) AND "
            + "(:updatedTime IS NULL OR c.updatedTime = :updatedTime) AND "
            + "(:createdUser IS NULL OR c.createdUser = :createdUser) AND "
            + "(:updatedUser IS NULL OR c.updatedUser = :updatedUser)")
    List<ConfigView> searchConfigViewsBy(
            @Param("configViewId") Long configViewId,
            @Param("viewName") String viewName,
            @Param("viewPath") String viewPath,
            @Param("apiPath") String apiPath,
            @Param("roleId") String roleId,
            @Param("status") Boolean status,
            @Param("createdTime") LocalDateTime createdTime,
            @Param("updatedTime") LocalDateTime updatedTime,
            @Param("createdUser") Long createdUser,
            @Param("updatedUser") Long updatedUser);

}
