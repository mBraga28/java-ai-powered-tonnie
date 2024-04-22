package me.dio.service;

import me.dio.dto.DietDTO;

import java.util.List;

public interface DietService {
    DietDTO getDietById(Long id);

    List<DietDTO> getAllDiets();

    List<DietDTO> getDietsByUser(Long userId);

    DietDTO createDiet(DietDTO diet);

    DietDTO updateDiet(DietDTO diet);

    void deleteDiet(Long id);
}

