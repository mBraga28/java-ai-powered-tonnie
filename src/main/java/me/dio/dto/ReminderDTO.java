package me.dio.dto;

import me.dio.domain.model.Reminder;
import me.dio.domain.model.User;

public class ReminderDTO {

    private Long id;

    private Long userId;
    private String type;
    private String description;
    private String time;

    public ReminderDTO() {}

    public ReminderDTO(Long id, Long userId, String type, String description, String time) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.description = description;
        this.time = time;
    }

    public ReminderDTO(Reminder entity) {
        id = entity.getId();
        userId = entity.getUser().getId();
        type = entity.getType();
        description = entity.getDescription();
        time = entity.getTime();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    
}

