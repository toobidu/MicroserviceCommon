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
    public List<ConfigView> getAllConfig() {
        return configViewRepository.findAll();
    }

    //Create new
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

    //Repository method

    @Override
    public Optional<ConfigView> findById(Long configViewId) {
        return configViewRepository.findByConfigViewId(configViewId);
    }

    @Override
    public Optional<ConfigView> findByViewName(String viewName) {
        return configViewRepository.findByViewName(viewName);
    }

    @Override
    public Optional<ConfigView> findByViewPath(String viewPath) {
        return configViewRepository.findByViewPath(viewPath);
    }

    @Override
    public Optional<ConfigView> findByApiPath(String apiPath) {
        return configViewRepository.findByApiPath(apiPath);
    }

    @Override
    public List<ConfigView> findByRoleId(Long roleId) {
        return configViewRepository.findByRoleId(roleId);
    }

    @Override
    public List<ConfigView> findByStatus(boolean status) {
        return configViewRepository.findByStatus(status);
    }

    @Override
    public List<ConfigView> findByCreatedTime(Date createdTime) {
        return configViewRepository.findByCreatedTime(createdTime);
    }

    @Override
    public List<ConfigView> findByUpdatedTime(Date updatedTime) {
        return configViewRepository.findByUpdatedTime(updatedTime);
    }

    @Override
    public List<ConfigView> findByCreatedUser(Long createdUser) {
        return configViewRepository.findByCreatedUser(createdUser);
    }

    @Override
    public List<ConfigView> findByUpdatedUser(Long updatedUser) {
        return configViewRepository.findByUpdatedUser(updatedUser);
    }

}
