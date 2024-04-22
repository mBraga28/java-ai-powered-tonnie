package me.dio.service;

import me.dio.dto.AppointmentDTO;

import java.util.List;

public interface AppointmentService {

    AppointmentDTO getAppointmentById(Long id);

    List<AppointmentDTO> getAllAppointments();

    List<AppointmentDTO> getAppointmentsByUser(Long userId);

    AppointmentDTO createAppointment(AppointmentDTO appointment);

    AppointmentDTO updateAppointment(AppointmentDTO appointment);

    void deleteAppointment(Long id);
}
