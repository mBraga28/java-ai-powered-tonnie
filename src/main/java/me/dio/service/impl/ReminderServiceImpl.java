package me.dio.service.impl;

import jakarta.transaction.Transactional;
import me.dio.domain.model.Reminder;
import me.dio.domain.model.Reminder;
import me.dio.domain.model.User;
import me.dio.domain.repository.ReminderRepository;
import me.dio.domain.repository.UserRepository;
import me.dio.dto.ReminderDTO;
import me.dio.dto.UserDTO;
import me.dio.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;

    private final UserRepository userRepository;

    public ReminderServiceImpl(ReminderRepository reminderRepository, UserRepository userRepository) {
        this.reminderRepository = reminderRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ReminderDTO getReminderById(Long id) {
        Optional<Reminder> obj = reminderRepository.findById(id);
        Reminder entity = obj.orElseThrow(() -> new NoSuchElementException("Entity not found"));
        return new ReminderDTO(entity);
    }

    @Override
    @Transactional
    public List<ReminderDTO> getAllReminders() {
        List<Reminder> list = reminderRepository.findAll(Sort.by("id"));
        return list.stream().map(ReminderDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ReminderDTO> getRemindersByUser(Long userId) {

        User user = userRepository.getReferenceById(userId);
        List<Reminder> list = reminderRepository.findByUser(user);
        return list.stream().map(ReminderDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReminderDTO createReminder(ReminderDTO dto) {

        if (dto.getId() != null && reminderRepository.existsById(dto.getId())) {
            throw new IllegalArgumentException("This Reminder ID already exists!");
        }
        Reminder entity = new Reminder();
        copyDtoToEntity(dto, entity);
        entity = reminderRepository.save(entity);
        return new ReminderDTO(entity);
    }

    @Override
    @Transactional
    public ReminderDTO updateReminder(ReminderDTO dto) {
        Optional<Reminder> optionalReminder = reminderRepository.findById(dto.getId());
        if (optionalReminder.isPresent()) {
            Reminder entity = optionalReminder.get();
            copyDtoToEntity(dto, entity);
            entity = reminderRepository.save(entity);
            return new ReminderDTO(entity);
        } else {
            throw new NoSuchElementException("Reminder not found");
        }
    }

    @Override
    @Transactional
    public void deleteReminder(Long id) {
        reminderRepository.deleteById(id);
    }

    private void copyDtoToEntity(ReminderDTO dto, Reminder entity) {
        entity.setUser(new User(dto.getId(), null, null, null, null, null));
        entity.setType(dto.getType());
        entity.setDescription(dto.getDescription());
        entity.setTime(dto.getTime());
    }
}

