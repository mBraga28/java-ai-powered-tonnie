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
    public AppointmentDTO createAppointment(AppointmentDTO dto) {
        if (dto.getId() != null && appointmentRepository.existsById(dto.getId())) {
            throw new IllegalArgumentException("This Appointment ID already exists!");
        }
        Appointment entity = new Appointment();
        copyDtoToEntity(dto, entity);
        entity = appointmentRepository.save(entity);
        return new AppointmentDTO(entity);
    }

    @Override
    @Transactional
    public AppointmentDTO updateAppointment(AppointmentDTO dto) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(dto.getId());
        if (optionalAppointment.isPresent()) {
            Appointment entity = optionalAppointment.get();
            copyDtoToEntity(dto, entity);
            entity = appointmentRepository.save(entity);
            return new AppointmentDTO(entity);
        } else {
            throw new NoSuchElementException("Appointment not found");
        }

    }

    @Override
    @Transactional
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    private void copyDtoToEntity(AppointmentDTO dto, Appointment entity) {
        entity.setUser(new User(dto.getId(), null, null, null, null, null));
        entity.setDoctor(dto.getDoctor());
        entity.setDate(dto.getDate());
        entity.setTime(dto.getTime());
        entity.setStatus(dto.getStatus());

    }

}

