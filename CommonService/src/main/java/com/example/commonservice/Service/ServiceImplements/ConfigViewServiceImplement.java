package com.example.commonservice.Service.ServiceImplements;

import com.example.commonservice.Model.Entity.ConfigView;
import com.example.commonservice.Repository.ConfigViewRepository;
import com.example.commonservice.Service.ConfigViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
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
    @Transactional(readOnly = false)
    public ConfigView createConfigView(ConfigView configView) {
        try {
            configView.setConfigViewId(null);
            if (configView.getStatus() == null) {
                configView.setStatus(true);
            }
            return configViewRepository.save(configView);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Congfig View code already exists", e);
        } catch (Exception e) {
            throw new RuntimeException("Error creating Config View", e);
        }
    }

    //Update
    @Override
    @Transactional(readOnly = false)
    public Optional<ConfigView> updateConfigView(Long configViewId, ConfigView configView) {
        try{
            return configViewRepository.findById(configViewId)
                    .map(existingConfigView -> {
                        //Update
                        existingConfigView.setViewName(configView.getViewName());
                        existingConfigView.setViewPath(configView.getViewPath());
                        existingConfigView.setApiPath(configView.getApiPath());
                        existingConfigView.setRoleId(configView.getRoleId());
                        existingConfigView.setStatus(configView.getStatus());
                        existingConfigView.setCreatedUser(configView.getCreatedUser());
                        existingConfigView.setUpdatedUser(configView.getUpdatedUser());
                        return configViewRepository.save(existingConfigView);

                    });
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("Congfig View code already exists", e);
        }catch (Exception e){
            throw new RuntimeException("Error creating Config View", e);
        }
    }

    //Delete
    @Override
    @Transactional(readOnly = false)
    public boolean deleteConfigView(Long configViewId) {
        try {
            if (configViewRepository.existsById(configViewId)) {
                configViewRepository.deleteById(configViewId);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting config view", e);
        }
    }

    //Search
    @Override
    public List<ConfigView> searchConfigView(Long configViewId, String viewName, String viewPath, String apiPath, String roleId, Boolean status, Date createdTime, Date updatedTime, Long createdUser, Long updatedUser) {
        return configViewRepository.searchConfigViewsBy(configViewId, viewName, viewPath, apiPath, roleId, status, createdTime, updatedTime, createdUser, updatedUser);
    }
}
