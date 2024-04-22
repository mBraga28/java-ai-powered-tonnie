package me.dio.controller;

import me.dio.dto.ActivityDTO;
import me.dio.dto.UserDTO;
import me.dio.service.ActivityService;
import me.dio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable Long id) {
        ActivityDTO activityDTO = activityService.getActivityById(id);
        return ResponseEntity.ok(activityDTO);
    }

    @GetMapping
    public ResponseEntity<List<ActivityDTO>> getAllActivities() {
        List<ActivityDTO> activities = activityService.getAllActivities();
        return ResponseEntity.ok(activities);
    }

    @PostMapping
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO) {
        ActivityDTO activityCreated = activityService.createActivity(activityDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(activityCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(activityCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable Long id, @RequestBody ActivityDTO activityDTO) {
        activityDTO.setId(id);
        ActivityDTO updatedActivity = activityService.updateActivity(activityDTO);
        return ResponseEntity.ok(updatedActivity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
}
