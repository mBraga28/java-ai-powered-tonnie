package me.dio.domain.repository;

import me.dio.domain.model.Appointment;
import me.dio.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByUser(User user);
}
