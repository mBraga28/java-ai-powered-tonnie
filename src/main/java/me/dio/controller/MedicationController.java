package me.dio.controller;

import me.dio.dto.MedicationDTO;
import me.dio.dto.UserDTO;
import me.dio.service.MedicationService;
import me.dio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/medications")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationDTO> getMedicationById(@PathVariable Long id) {
        MedicationDTO medicationDTO = medicationService.getMedicationById(id);
        return ResponseEntity.ok(medicationDTO);
    }

    @GetMapping
    public ResponseEntity<List<MedicationDTO>> getAllMedications() {
        List<MedicationDTO> medications = medicationService.getAllMedications();
        return ResponseEntity.ok(medications);
    }

    @PostMapping
    public ResponseEntity<MedicationDTO> createMedication(@RequestBody MedicationDTO medicationDTO) {
        MedicationDTO medicationCreated = medicationService.createMedication(medicationDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(medicationCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(medicationCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicationDTO> updateMedication(@PathVariable Long id, @RequestBody MedicationDTO medicationDTO) {
        medicationDTO.setId(id);
        MedicationDTO updatedMedication = medicationService.updateMedication(medicationDTO);
        return ResponseEntity.ok(updatedMedication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
        return ResponseEntity.noContent().build();
    }
}
