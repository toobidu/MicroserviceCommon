package com.example.commonservice.Repository;

import com.example.commonservice.Model.Entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FilesRepository extends JpaRepository<Files, Long> {

    @Query("SELECT c FROM Files c WHERE "
            + "(:fileId IS NULL OR c.fileId = :fileId) AND "
            + "(:fileName IS NULL OR c.fileName = :fileName) AND "
            + "(:filePath IS NULL OR c.filePath = :filePath) AND "
            + "(:businessCode IS NULL OR c.businessCode = :businessCode) AND "
            + "(:businessId IS NULL OR c.businessId= :businessId) AND"
            + "(:status IS NULL OR c.status = :status) AND "
            + "(:createdTime IS NULL OR c.createdTime = :createdTime) AND "
            + "(:updatedTime IS NULL OR c.updatedTime = :updatedTime) AND "
            + "(:createdUser IS NULL OR c.createdUser = :createdUser) AND "
            + "(:updatedUser IS NULL OR c.updatedUser = :updatedUser)")
    List<Files> searchFilesBy(
            @Param("fileId") Long fileId,
            @Param("fileName") String fileName,
            @Param("filePath") String filePath,
            @Param("businessCode") String businessCode,
            @Param("businessId") Long businessId,
            @Param("status") Boolean status,
            @Param("createdTime") LocalDateTime createdTime,
            @Param("updatedTime") LocalDateTime updatedTime,
            @Param("createdUser") Long createdUser,
            @Param("updatedUser") Long updatedUser);

}
