package me.dio.dto;

import me.dio.domain.model.Medication;

public class MedicationDTO {

    private Long id;

    private Long userId;
    private String name;
    private String dosage;
    private String schedule;
    private String status;

    public MedicationDTO() {}

    public MedicationDTO(Long id, Long userId, String name, String dosage, String schedule, String status) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.dosage = dosage;
        this.schedule = schedule;
        this.status = status;
    }

    public MedicationDTO(Medication entity) {
        id = entity.getId();
        userId = entity.getUser().getId();
        name = entity.getName();
        dosage = entity.getDosage();
        schedule = entity.getSchedule();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}

