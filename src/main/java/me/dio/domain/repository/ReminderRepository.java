package me.dio.domain.repository;

import me.dio.domain.model.Reminder;
import me.dio.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    List<Reminder> findByUser(User user);
}
