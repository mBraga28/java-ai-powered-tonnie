package me.dio.service;

import me.dio.dto.AppointmentDTO;

import java.util.List;

public interface AppointmentService {

    AppointmentDTO getAppointmentById(Long id);

    List<AppointmentDTO> getAllAppointments();

    List<AppointmentDTO> getAppointmentsByUser(Long userId);

    void saveAppointment(AppointmentDTO appointment);

    void updateAppointment(AppointmentDTO appointment);

    void deleteAppointment(Long id);
}
