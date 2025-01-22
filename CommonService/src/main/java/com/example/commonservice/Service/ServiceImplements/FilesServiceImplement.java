package com.example.commonservice.Service.ServiceImplements;

import com.example.commonservice.Model.Entity.Files;
import com.example.commonservice.Repository.FilesRepository;
import com.example.commonservice.Service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FilesServiceImplement implements FilesService {

    private final FilesRepository filesRepository;

    @Autowired
    public FilesServiceImplement(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    //Get all
    @Override
    public List<Files> getAllFiles() {
        return filesRepository.findAll();
    }

    //Create
    @Override
    @Transactional
    public Files createFile(Files file) {
        return filesRepository.save(file);
    }

    //Update
    @Override
    @Transactional
    public Optional<Files> updateFile(Long fileId, Files file) {
       if (filesRepository.existsById(fileId)) {
           file.setFileId(fileId);
           return Optional.of(filesRepository.save(file));
       }
       return Optional.empty();
    }

    //Delete
    @Override
    @Transactional
    public boolean deleteFile(Long fileId) {
        if (filesRepository.existsById(fileId)) {
            filesRepository.deleteById(fileId);
            return true;
        }
        return false;
    }

    //Search

    @Override
    public List<Files> searchFiles(Long fileId, String fileName, String filePath, String businessCode, Long businessId, Boolean status, Long createdUser, Long updatedUser, Date createdTime, Date updatedTime) {
        return filesRepository.searchFilesBy(fileId, fileName, filePath, businessCode, businessId, status, createdTime, updatedTime, createdUser, updatedUser);
    }
}
