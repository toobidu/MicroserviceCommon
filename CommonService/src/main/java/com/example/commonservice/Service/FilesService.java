package com.example.commonservice.Service;

import com.example.commonservice.Model.Entity.Files;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FilesService {

    //Get all
    List<Files> getAllFiles();

    //Create
    Files createFile(Files file);

    //Update
    Optional<Files> updateFile(Long fileId, Files file);

    //Delete
    boolean deleteFile(Long fileId);

    //Search
    List<Files> searchFiles(Long fileId, String fileName, String filePath, String businessCode, Long businessId, Boolean status, Long createdUser, Long updatedUser, Date createdTime, Date updatedTime);


}
