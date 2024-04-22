package me.dio.service;

import me.dio.dto.MedicationDTO;

import java.util.List;

public interface MedicationService {

    MedicationDTO getMedicationById(Long id);

    List<MedicationDTO> getAllMedications();

    List<MedicationDTO> getMedicationsByUser(Long userId);

    void saveMedication(MedicationDTO medication);

    void updateMedication(MedicationDTO medication);

    void deleteMedication(Long id);
}

