package me.dio.service;

import me.dio.dto.ActivityDTO;

import java.util.List;

public interface ActivityService {

    ActivityDTO getActivityById(Long id);
    List<ActivityDTO> getAllActivities();
    List<ActivityDTO> getActivitiesByUser(Long userId);
    void saveActivity(ActivityDTO activity);
    void updateActivity(ActivityDTO activity);
    void deleteActivity(Long id);
}
