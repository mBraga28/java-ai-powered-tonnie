package me.dio.dto;

import me.dio.domain.model.Activity;

public class ActivityDTO {

    private Long id;

    private Long userId;
    private String type;
    private float distance;
    private String time;
    private int burnedCalories;

    public ActivityDTO() {}

    public ActivityDTO(Long id, Long userId, String type, float distance, String time, int burnedCalories) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.distance = distance;
        this.time = time;
        this.burnedCalories = burnedCalories;
    }

    public ActivityDTO(Activity entity) {
        id = entity.getId();
        userId = entity.getUser().getId();
        type = entity.getType();
        distance = entity.getDistance();
        time = entity.getTime();
        burnedCalories = entity.getBurnedCalories();
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

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(int burnedCalories) {
        this.burnedCalories = burnedCalories;
    }


}

