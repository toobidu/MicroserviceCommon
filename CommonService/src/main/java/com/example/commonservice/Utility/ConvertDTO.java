package com.example.commonservice.Utility;

import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ConvertDTO {

    // Convert Entity to DTO (Help to control data which is sent to the client)
    public static <E, D> D convertToDTO(E entity, Class<D> dtoClass) {
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert Entity to DTO", e);
        }
    }

    // Convert DTO to Entity (Help to give and process data from the client)
    public static <D, E> E convertToEntity(D dto, Class<E> entityClass) {
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dto, entity);
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert DTO to Entity", e);
        }
    }

    // Convert List<Entity> to List<DTO> (Help to avoi repeating conversion code in each method)
    public static <E, D> List<D> convertToDTOList(List<E> entities, Class<D> dtoClass) {
        return entities.stream()
                .map(entity -> convertToDTO(entity, dtoClass))
                .collect(Collectors.toList());
    }

    // Convert List<DTO> to List<Entity>
    public static <D, E> List<E> convertToEntityList(List<D> dtos, Class<E> entityClass) {
        return dtos.stream()
                .map(dto -> convertToEntity(dto, entityClass))
                .collect(Collectors.toList());
    }
}
