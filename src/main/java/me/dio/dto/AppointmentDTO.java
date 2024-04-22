package me.dio.dto;

import me.dio.domain.model.Appointment;

import java.util.Date;

public class AppointmentDTO {

    private Long id;

    private Long userId;
    private String doctor;
    private Date date;
    private String time;
    private String status;

    public AppointmentDTO() {}

    public AppointmentDTO(Long id, Long userId, String doctor, Date date, String time, String status) {
        this.id = id;
        this.userId = userId;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public AppointmentDTO(Appointment entity) {
        id = entity.getId();
        userId = entity.getUser().getId();
        doctor = entity.getDoctor();
        date = entity.getDate();
        time = entity.getTime();
        status = entity.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
