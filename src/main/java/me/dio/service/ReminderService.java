package me.dio.service;

import me.dio.domain.model.Reminder;
import me.dio.domain.model.User;
import me.dio.dto.ReminderDTO;
import me.dio.dto.UserDTO;

import java.util.List;

public interface ReminderService {

    ReminderDTO getReminderById(Long id);

    List<ReminderDTO> getAllReminders();

    List<ReminderDTO> getRemindersByUser(Long userId);

    ReminderDTO createReminder(ReminderDTO reminder);

    ReminderDTO updateReminder(ReminderDTO reminder);

    void deleteReminder(Long id);
}


