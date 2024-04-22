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
    public ActivityDTO createActivity(ActivityDTO dto) {

        if (dto.getId() != null && activityRepository.existsById(dto.getId())) {
            throw new IllegalArgumentException("This Activity ID already exists!");
        }
        Activity entity = new Activity();
        copyDtoToEntity(dto, entity);
        entity = activityRepository.save(entity);
        return new ActivityDTO(entity);
    }

    @Override
    @Transactional
    public ActivityDTO updateActivity(ActivityDTO dto) {
        Optional<Activity> optionalActivity = activityRepository.findById(dto.getId());
        if (optionalActivity.isPresent()) {
            Activity activity = optionalActivity.get();
            copyDtoToEntity(dto, activity);
            activity = activityRepository.save(activity);
            return new ActivityDTO(activity);
        } else {
            throw new NoSuchElementException("Activity not found");
        }
    }

    @Override
    @Transactional
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    private void copyDtoToEntity(ActivityDTO dto, Activity entity) {
        entity.setUser(new User(dto.getId(), null, null, null, null, null));
        entity.setType(dto.getType());
        entity.setDistance(dto.getDistance());
        entity.setTime(dto.getTime());
        entity.setBurnedCalories(dto.getBurnedCalories());
    }
}

