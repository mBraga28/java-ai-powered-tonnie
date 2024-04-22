package me.dio.service.impl;

import jakarta.transaction.Transactional;
import me.dio.domain.model.Diet;
import me.dio.domain.model.User;
import me.dio.domain.repository.DietRepository;
import me.dio.domain.repository.UserRepository;
import me.dio.dto.DietDTO;
import me.dio.service.DietService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DietServiceImpl implements DietService {

    private final DietRepository dietRepository;

    private final UserRepository userRepository;

    public DietServiceImpl(DietRepository dietRepository, UserRepository userRepository) {
        this.dietRepository = dietRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public DietDTO getDietById(Long id) {
        Optional<Diet> obj = dietRepository.findById(id);
        Diet entity = obj.orElseThrow(() -> new NoSuchElementException("Entity not found"));
        return new DietDTO(entity);
    }

    @Override
    @Transactional
    public List<DietDTO> getAllDiets() {
        List<Diet> list = dietRepository.findAll(Sort.by("id"));
        return list.stream().map(DietDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<DietDTO> getDietsByUser(Long userId) {

        User user = userRepository.getReferenceById(userId);
        List<Diet> list = dietRepository.findByUser(user);
        return list.stream().map(DietDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DietDTO createDiet(DietDTO dto) {
        if (dto.getId() != null && dietRepository.existsById(dto.getId())) {
            throw new IllegalArgumentException("This Diet ID already exists!");
        }
        Diet entity = new Diet();
        copyDtoToEntity(dto, entity);
        entity = dietRepository.save(entity);
        return new DietDTO(entity);
    }

    @Override
    @Transactional
    public DietDTO updateDiet(DietDTO dto) {
        Optional<Diet> optionalDiet = dietRepository.findById(dto.getId());
        if (optionalDiet.isPresent()) {
            Diet entity = optionalDiet.get();
            copyDtoToEntity(dto, entity);
            entity = dietRepository.save(entity);
            return new DietDTO(entity);
        } else {
            throw new NoSuchElementException("Diet not found");
        }
    }

    @Override
    @Transactional
    public void deleteDiet(Long id) {
        dietRepository.deleteById(id);
    }

    private void copyDtoToEntity(DietDTO dietDTO, Diet diet) {
        diet.setUser(new User(dietDTO.getId(), null, null, null, null, null));
        diet.setFoods(dietDTO.getFoods());
        diet.setConsumedCalories(dietDTO.getConsumedCalories());
    }


}

