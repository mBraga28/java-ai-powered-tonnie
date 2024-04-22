package me.dio.service.impl;

import jakarta.transaction.Transactional;
import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.dto.UserDTO;
import me.dio.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDTO findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new NoSuchElementException("Entity not found"));
        return new UserDTO(entity);
    }

    @Override
    @Transactional
    public UserDTO create(UserDTO userToCreate) {
        if (userToCreate.getId() != null && userRepository.existsById(userToCreate.getId())) {
            throw new IllegalArgumentException("This User ID already exists!");
        }

        User entity = new User();
        copyDtoToEntity(userToCreate, entity);
        entity = userRepository.save(entity);
        return new UserDTO(entity);
    }

    private void copyDtoToEntity(UserDTO userToCreate, User entity) {
        entity.setName(userToCreate.getName());
        entity.setBirthDate(userToCreate.getBirthDate());
        entity.setHeight(userToCreate.getHeight());
        entity.setWeight(userToCreate.getWeight());
        entity.setGoal(userToCreate.getGoal());
    }
}
