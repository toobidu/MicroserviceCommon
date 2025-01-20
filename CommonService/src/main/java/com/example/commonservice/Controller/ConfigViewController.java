package com.example.commonservice.Controller;

import com.example.commonservice.Model.DTO.ConfigViewDTO;
import com.example.commonservice.Model.Entity.ConfigView;
import com.example.commonservice.Service.ConfigViewService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/common/front-end/config-view")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ConfigViewController {

    private final ConfigViewService configViewService;

    @Autowired
    public ConfigViewController(ConfigViewService configViewService) {
        this.configViewService = configViewService;
    }

    //Get all
    @GetMapping
    public ResponseEntity<List<ConfigView>> getAllConfigView() {
        List<ConfigView> configViews = configViewService.getAllConfig();
        return ResponseEntity.ok(convertToDTOList(configViews));
    }



}
