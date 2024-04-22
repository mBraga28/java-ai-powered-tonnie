package me.dio.service;

import me.dio.dto.ActivityDTO;

import java.util.List;

public interface ActivityService {

    ActivityDTO getActivityById(Long id);
    List<ActivityDTO> getAllActivities();
    List<ActivityDTO> getActivitiesByUser(Long userId);
    ActivityDTO createActivity(ActivityDTO activity);
    ActivityDTO updateActivity(ActivityDTO activity);
    void deleteActivity(Long id);
}
