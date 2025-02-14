package com.example.commonservice.Controller;

import com.example.commonservice.Model.Entity.ConfigView;
import com.example.commonservice.Service.ConfigViewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/common/front-end/config-view")
public class ConfigViewController {

    private final ConfigViewService configViewService;

    @Autowired
    public ConfigViewController(ConfigViewService configViewService) {
        this.configViewService = configViewService;
    }

    //Get all
    @GetMapping
    public ResponseEntity<List<ConfigView>> getAllConfigViews() {
        List<ConfigView> configViews = configViewService.getAllConfigView();
        return ResponseEntity.ok(configViews);
    }

    //Create
    @PostMapping
    public ResponseEntity<ConfigView> createConfigView(@RequestBody @Valid ConfigView configView) {
        ConfigView createdConfigView = configViewService.createConfigView(configView);
        return ResponseEntity.ok(createdConfigView);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<ConfigView> updateConfigView(@RequestBody @Valid ConfigView configView, @PathVariable Long id) {
        Optional<ConfigView> updatedConfigView = configViewService.updateConfigView(id, configView);
        return updatedConfigView.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteConfigView(@PathVariable Long id) {
        boolean deleted = configViewService.deleteConfigView(id);
        if (deleted) {
            return ResponseEntity.ok("Config View is deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error!");
        }

    }

    //Search
    @GetMapping("/search")
    public ResponseEntity<List<ConfigView>> searchConfigView(
            @RequestParam(required = false) Long configViewId,
            @RequestParam(required = false) String viewName,
            @RequestParam(required = false) String viewPath,
            @RequestParam(required = false) String apiPath,
            @RequestParam(required = false) String roleId,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime createdTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime updatedTime,
            @RequestParam(required = false) Long createdUser,
            @RequestParam(required = false) Long updatedUser
    ) {

        List<ConfigView> configViews = configViewService.searchConfigView(configViewId, viewName, viewPath, apiPath, roleId, status, createdTime, updatedTime, createdUser, updatedUser);
        return ResponseEntity.ok(configViews);
    }
}
