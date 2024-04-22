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
    public void saveReminder(ReminderDTO reminderDTO) {
        Reminder reminder = new Reminder();
        copyDtoToEntity(reminderDTO, reminder);
        reminderRepository.save(reminder);
    }

    @Override
    @Transactional
    public void updateReminder(ReminderDTO reminderDTO) {
        Optional<Reminder> optionalReminder = reminderRepository.findById(reminderDTO.getId());
        if (optionalReminder.isPresent()) {
            Reminder reminder = optionalReminder.get();
            copyDtoToEntity(reminderDTO, reminder);
            reminderRepository.save(reminder);
        } else {
            throw new NoSuchElementException("Reminder not found");
        }
    }

    @Override
    @Transactional
    public void deleteReminder(Long id) {
        reminderRepository.deleteById(id);
    }

    private void copyDtoToEntity(ReminderDTO reminderDTO, Reminder reminder) {
        reminder.setUser(new User(reminderDTO.getId(), null, null, null, null, null));
        reminder.setType(reminderDTO.getType());
        reminder.setDescription(reminderDTO.getDescription());
        reminder.setTime(reminderDTO.getTime());
    }
}

