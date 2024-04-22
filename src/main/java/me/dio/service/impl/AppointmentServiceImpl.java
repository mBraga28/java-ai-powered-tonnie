package me.dio.service.impl;

import jakarta.transaction.Transactional;
import me.dio.domain.model.Appointment;
import me.dio.domain.model.User;
import me.dio.domain.repository.AppointmentRepository;
import me.dio.domain.repository.UserRepository;
import me.dio.dto.AppointmentDTO;
import me.dio.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final UserRepository userRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public AppointmentDTO getAppointmentById(Long id) {
        Optional<Appointment> obj = appointmentRepository.findById(id);
        Appointment entity = obj.orElseThrow(() -> new NoSuchElementException("Entity not found"));
        return new AppointmentDTO(entity);
    }

    @Override
    @Transactional
    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> list = appointmentRepository.findAll();
        return list.stream().map(AppointmentDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<AppointmentDTO> getAppointmentsByUser(Long userId) {
        User user = userRepository.getReferenceById(userId);
        List<Appointment> list = appointmentRepository.findByUser(user);
        return list.stream().map(AppointmentDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveAppointment(AppointmentDTO dto) {
        Appointment entity = new Appointment();
        copyDtoToEntity(dto, entity);
        appointmentRepository.save(entity);
    }

    @Override
    @Transactional
    public void updateAppointment(AppointmentDTO appointmentDTO) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentDTO.getId());
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            copyDtoToEntity(appointmentDTO, appointment);
            appointmentRepository.save(appointment);
        } else {
            throw new NoSuchElementException("Appointment not found");
        }

    }

    @Override
    @Transactional
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    private void copyDtoToEntity(AppointmentDTO appointmentDTO, Appointment entity) {
        entity.setUser(new User(appointmentDTO.getId(), null, null, null, null, null));
        entity.setDoctor(appointmentDTO.getDoctor());
        entity.setDate(appointmentDTO.getDate());
        entity.setTime(appointmentDTO.getTime());
        entity.setStatus(appointmentDTO.getStatus());

    }

}

