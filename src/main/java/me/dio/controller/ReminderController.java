package me.dio.controller;

import me.dio.dto.ReminderDTO;
import me.dio.service.ReminderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/reminders")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReminderDTO> getReminderById(@PathVariable Long id) {
        ReminderDTO reminderDTO = reminderService.getReminderById(id);
        return ResponseEntity.ok(reminderDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReminderDTO>> getAllReminders() {
        List<ReminderDTO> reminders = reminderService.getAllReminders();
        return ResponseEntity.ok(reminders);
    }

    @PostMapping
    public ResponseEntity<ReminderDTO> createReminder(@RequestBody ReminderDTO reminderDTO) {
        ReminderDTO reminderCreated = reminderService.createReminder(reminderDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reminderCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(reminderCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReminderDTO> updateReminder(@PathVariable Long id, @RequestBody ReminderDTO reminderDTO) {
        reminderDTO.setId(id);
        ReminderDTO updatedReminder = reminderService.updateReminder(reminderDTO);
        return ResponseEntity.ok(updatedReminder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
        return ResponseEntity.noContent().build();
    }
}

