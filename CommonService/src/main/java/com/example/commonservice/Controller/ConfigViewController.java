package com.example.commonservice.Controller;

import com.example.commonservice.Model.DTO.CategoryDTO;
import com.example.commonservice.Model.DTO.ConfigViewDTO;
import com.example.commonservice.Model.Entity.Category;
import com.example.commonservice.Model.Entity.ConfigView;
import com.example.commonservice.Model.Response.ApiResponse;
import com.example.commonservice.Service.ConfigViewService;
import com.example.commonservice.Utility.ConvertDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;

@RestController
@RequestMapping("/api/common/front-end/config-view")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Validated
@Slf4j
public class ConfigViewController {
    private final ConfigViewService configViewService;

    @Autowired
    public ConfigViewController(ConfigViewService configViewService) {
        this.configViewService = configViewService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ConfigViewDTO>>> getAllConfig() {
        try {
            List<ConfigView> configViews = configViewService.getAllConfig();
            List<ConfigViewDTO> dtos = ConvertDTO.convertToDTOList(configViews, ConfigViewDTO.class);
            return ResponseEntity.ok(ApiResponse.success(dtos));
        } catch (Exception e) {
            log.error("Error getting all config view: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Failed to retrieve config views"));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ConfigViewDTO>> createConfigView(@Valid @RequestBody ConfigViewDTO configViewDTO) {
        try {
            ConfigView configView = ConvertDTO.convertToEntity(configViewDTO, ConfigView.class);
            ConfigView createConfigView = configViewService.createConfigView(configView);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(ConvertDTO.convertToDTO(createConfigView, ConfigViewDTO.class)));
        } catch (Exception e) {
            log.error("Error creating config view: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Failed to create config view"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ConfigViewDTO>> updateConfigView(@PathVariable Long id, @Valid @RequestBody ConfigViewDTO configViewDTO) {
        try {
            ConfigView configView = ConvertDTO.convertToEntity(configViewDTO, ConfigView.class);
            return configViewService.updateConfigView(id, configView).map(update -> ResponseEntity.ok(ApiResponse.success(ConvertDTO.convertToDTO(update, ConfigViewDTO.class)))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("ConfigView not found")));
        } catch (Exception e) {
            log.error("Error updating category: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Failed to update category"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteConfigView(@PathVariable Long id) {
        try {
            boolean isDeleted = configViewService.deleteConfigView(id);
            if (isDeleted) {
                return ResponseEntity.ok(ApiResponse.success(null, "ConfigView deleted successfully"));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("ConfigView not found"));
        } catch (Exception e) {
            log.error("Error deleting ConfigView: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Failed to delete config view"));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ConfigViewDTO>>> searchConfigView(@RequestParam(required = false) Long configViewId, @RequestParam(required = false) String viewName, @RequestParam(required = false) String viewPath, @RequestParam(required = false) String apiPath, @RequestParam(required = false) Long roleId, @RequestParam(required = false) Boolean status, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdTime, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date updatedTime, @RequestParam(required = false) Long createdUser, @RequestParam(required = false) Long updatedUser) {

        try {
            // Tạo LinkedHashMap để đảm bảo duyệt qua theo thứ tự thêm vào
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("configViewId", configViewId);
            params.put("viewName", viewName);
            params.put("viewPath", viewPath);
            params.put("apiPath", apiPath);
            params.put("roleId", roleId);

            // Kiểm tra các tham số không null và xử lý
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() != null) {
                    Optional<ConfigView> configViewOpt = switch (entry.getKey()) {
                        case "configViewId" -> configViewService.findById((Long) entry.getValue());
                        case "viewName" -> configViewService.findByViewName((String) entry.getValue());
                        case "viewPath" -> configViewService.findByViewPath((String) entry.getValue());
                        case "apiPath" -> configViewService.findByApiPath((String) entry.getValue());
//                        case "roleId" -> configViewService.findByRoleId((Long) entry.getValue());
                        default -> Optional.empty();
                    };

                    return configViewOpt
                            .map(configView -> ResponseEntity.ok(ApiResponse.success(
                                    List.of(ConvertDTO.convertToDTO(configView, ConfigViewDTO.class)))))
                            .orElse(ResponseEntity.ok(ApiResponse.success(Collections.emptyList())));
                }
            }

            // Xử lý các tham số trả về danh sách
            List<ConfigView> configViews = handleListParams(status, createdTime, updatedTime, createdUser, updatedUser);

            return ResponseEntity.ok(ApiResponse.success(ConvertDTO.convertToDTOList(configViews, ConfigViewDTO.class)));
        } catch (Exception e) {
            log.error("Error searching config views: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Failed to search config views"));
        }
    }

    // Phương thức phụ xử lý các tham số trả về danh sách
    private List<ConfigView> handleListParams(Boolean status, Date createdTime, Date updatedTime, Long createdUser, Long updatedUser) {
        if (status != null) {
            return configViewService.findByStatus(status);
        } else if (createdTime != null) {
            return configViewService.findByCreatedTime(createdTime);
        } else if (updatedTime != null) {
            return configViewService.findByUpdatedTime(updatedTime);
        } else if (createdUser != null) {
            return configViewService.findByCreatedUser(createdUser);
        } else if (updatedUser != null) {
            return configViewService.findByUpdatedUser(updatedUser);
        }
        return Collections.emptyList();
    }
}
