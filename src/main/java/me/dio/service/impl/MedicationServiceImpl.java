package me.dio.service.impl;

import jakarta.transaction.Transactional;
import me.dio.domain.model.Diet;
import me.dio.domain.model.Medication;
import me.dio.domain.model.User;
import me.dio.domain.repository.MedicationRepository;
import me.dio.domain.repository.UserRepository;
import me.dio.dto.DietDTO;
import me.dio.dto.MedicationDTO;
import me.dio.service.MedicationService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;

    private final UserRepository userRepository;

    public MedicationServiceImpl(MedicationRepository medicationRepository, UserRepository userRepository) {
        this.medicationRepository = medicationRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public MedicationDTO getMedicationById(Long id) {
        Optional<Medication> obj = medicationRepository.findById(id);
        Medication entity = obj.orElseThrow(() -> new NoSuchElementException("Entity not found"));
        return new MedicationDTO(entity);
    }

    @Override
    @Transactional
    public List<MedicationDTO> getAllMedications() {
        List<Medication> list = medicationRepository.findAll(Sort.by("id"));
        return list.stream().map(MedicationDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<MedicationDTO> getMedicationsByUser(Long userId) {

        User user = userRepository.getReferenceById(userId);
        List<Medication> list = medicationRepository.findByUser(user);
        return list.stream().map(MedicationDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveMedication(MedicationDTO medicationDTO) {
        Medication entity = new Medication();
        copyDtoToEntity(medicationDTO, entity);
        medicationRepository.save(entity);
    }

    @Override
    @Transactional
    public void updateMedication(MedicationDTO medicationDTO) {
        Optional<Medication> optionalDiet = medicationRepository.findById(medicationDTO.getId());
        if (optionalDiet.isPresent()) {
            Medication medication = optionalDiet.get();
            copyDtoToEntity(medicationDTO, medication);
            medicationRepository.save(medication);
        } else {
            throw new NoSuchElementException("Medication not found");
        }
    }

    @Override
    @Transactional
    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }

    private void copyDtoToEntity(MedicationDTO medicationDTO, Medication medication) {
        medication.setUser(new User(medicationDTO.getId(), null, null, null, null, null));
        medication.setName(medicationDTO.getName());
        medication.setDosage(medicationDTO.getDosage());
        medication.setSchedule(medicationDTO.getSchedule());
        medication.setStatus(medicationDTO.getStatus());
    }

}

