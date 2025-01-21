package com.example.commonservice.Service.ServiceImplements;

import com.example.commonservice.Model.Entity.ConfigView;
import com.example.commonservice.Repository.ConfigViewRepository;
import com.example.commonservice.Service.ConfigViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ConfigViewServiceImplement implements ConfigViewService {

    private final ConfigViewRepository configViewRepository;

    @Autowired
    public ConfigViewServiceImplement(ConfigViewRepository configViewRepository) {
        this.configViewRepository = configViewRepository;
    }

    //Get all
    @Override
    public List<ConfigView> getAllConfigView() {
        return configViewRepository.findAll();
    }

    //Create
    @Override
    @Transactional
    public ConfigView createConfigView(ConfigView configView) {
        return configViewRepository.save(configView);
    }

    //Update
    @Override
    @Transactional
    public Optional<ConfigView> updateConfigView(Long configViewId, ConfigView configView) {
        if (configViewRepository.existsById(configViewId)) {
            configView.setConfigViewId(configViewId);
            return Optional.of(configViewRepository.save(configView));
        }
        return Optional.empty();
    }

    //Delete
    @Override
    @Transactional
    public boolean deleteConfigView(Long configViewId) {
        if (configViewRepository.existsById(configViewId)) {
            configViewRepository.deleteById(configViewId);
            return true;
        }
        return false;
    }

    //Search
    @Override
    public List<ConfigView> searchConfigView(Long configViewId, String viewName, String viewPath, String apiPath, Long roleId, Boolean status, Date createdTime, Date updatedTime, Long createdUser, Long updatedUser) {
        return configViewRepository.searchConfigViewsBy(configViewId, viewName, viewPath, apiPath, roleId, status, createdTime, updatedTime, createdUser, updatedUser);
    }
}
