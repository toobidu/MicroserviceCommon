package com.example.commonservice.Controller;

import com.example.commonservice.Model.Entity.Files;
import com.example.commonservice.Service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/common/front-end/files")
public class FilesController {

    private final FilesService filesService;

    @Autowired
    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    //Get all
    @GetMapping
    public ResponseEntity<List<Files>> getAllFiles() {
        List<Files> files = filesService.getAllFiles();
        return ResponseEntity.ok(files);
    }

    //Create
    @PostMapping
    public ResponseEntity<Files> createFiles(@RequestBody Files files) {
        Files file = filesService.createFile(files);
        return ResponseEntity.ok(file);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Files> updateFiles(@PathVariable Long id, @RequestBody Files files) {
        Optional<Files> updateFile = filesService.updateFile(id, files);
        return updateFile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFiles(@PathVariable Long id) {
        boolean deleted = filesService.deleteFile(id);
        if (deleted) {
            return ResponseEntity.ok("File is deleted successfully!");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found!");
        }
    }

    //Search
    @GetMapping("/search")
    public ResponseEntity<List<Files>> searchFiles(
            @RequestParam(required = false) Long fileId,
            @RequestParam(required = false) String fileName,
            @RequestParam(required = false) String filePath,
            @RequestParam(required = false) String businessCode,
            @RequestParam(required = false) Long businessId,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date updatedTime,
            @RequestParam(required = false) Long createdUser,
            @RequestParam(required = false) Long updatedUser
    ) {
        List<Files> files = filesService.searchFiles(fileId, fileName, filePath, businessCode, businessId, status, createdUser, updatedUser, createdTime, updatedTime);
        return ResponseEntity.ok(files);
    }
}
