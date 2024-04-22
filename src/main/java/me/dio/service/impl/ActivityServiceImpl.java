package me.dio.service.impl;

import jakarta.transaction.Transactional;
import me.dio.domain.model.Activity;
import me.dio.domain.model.User;
import me.dio.domain.repository.ActivityRepository;
import me.dio.domain.repository.UserRepository;
import me.dio.dto.ActivityDTO;
import me.dio.service.ActivityService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    private final UserRepository userRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository, UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ActivityDTO getActivityById(Long id) {
        Optional<Activity> obj = activityRepository.findById(id);
        Activity entity = obj.orElseThrow(() -> new NoSuchElementException("Entity not found"));
        return new ActivityDTO(entity);
    }

    @Override
    @Transactional
    public List<ActivityDTO> getAllActivities() {
        List<Activity> list = activityRepository.findAll(Sort.by("id"));
        return list.stream().map(ActivityDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ActivityDTO> getActivitiesByUser(Long userId) {

        User user = userRepository.getReferenceById(userId);
        List<Activity> list = activityRepository.findByUser(user);
        return list.stream().map(ActivityDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveActivity(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        copyDtoToEntity(activityDTO, activity);
        activityRepository.save(activity);
    }

    @Override
    @Transactional
    public void updateActivity(ActivityDTO activityDTO) {
        Optional<Activity> optionalActivity = activityRepository.findById(activityDTO.getId());
        if (optionalActivity.isPresent()) {
            Activity activity = optionalActivity.get();
            copyDtoToEntity(activityDTO, activity);
            activityRepository.save(activity);
        } else {
            throw new NoSuchElementException("Activity not found");
        }
    }

    @Override
    @Transactional
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    private void copyDtoToEntity(ActivityDTO activityDTO, Activity entity) {
        entity.setUser(new User(activityDTO.getId(), null, null, null, null, null));
        entity.setType(activityDTO.getType());
        entity.setDistance(activityDTO.getDistance());
        entity.setTime(activityDTO.getTime());
        entity.setBurnedCalories(activityDTO.getBurnedCalories());
    }
}

