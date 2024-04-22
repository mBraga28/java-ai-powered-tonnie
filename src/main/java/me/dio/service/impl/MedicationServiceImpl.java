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
    public MedicationDTO createMedication(MedicationDTO dto) {
        if (dto.getId() != null && medicationRepository.existsById(dto.getId())) {
            throw new IllegalArgumentException("This Medication ID already exists!");
        }
        Medication entity = new Medication();
        copyDtoToEntity(dto, entity);
        entity = medicationRepository.save(entity);
        return new MedicationDTO(entity);
    }

    @Override
    @Transactional
    public MedicationDTO updateMedication(MedicationDTO dto) {
        Optional<Medication> optionalDiet = medicationRepository.findById(dto.getId());
        if (optionalDiet.isPresent()) {
            Medication entity = optionalDiet.get();
            copyDtoToEntity(dto, entity);
            entity = medicationRepository.save(entity);
            return new MedicationDTO(entity);
        } else {
            throw new NoSuchElementException("Medication not found");
        }
    }

    @Override
    @Transactional
    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }

    private void copyDtoToEntity(MedicationDTO dto, Medication entity) {
        entity.setUser(new User(dto.getId(), null, null, null, null, null));
        entity.setName(dto.getName());
        entity.setDosage(dto.getDosage());
        entity.setSchedule(dto.getSchedule());
        entity.setStatus(dto.getStatus());
    }

}

