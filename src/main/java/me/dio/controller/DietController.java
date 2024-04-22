package me.dio.controller;

import me.dio.dto.AppointmentDTO;
import me.dio.dto.DietDTO;
import me.dio.dto.UserDTO;
import me.dio.service.DietService;
import me.dio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/diets")
public class DietController {

    private final DietService dietService;

    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietDTO> getDietById(@PathVariable Long id) {
        DietDTO dietDTO = dietService.getDietById(id);
        return ResponseEntity.ok(dietDTO);
    }

    @GetMapping
    public ResponseEntity<List<DietDTO>> getAllDiets() {
        List<DietDTO> diets = dietService.getAllDiets();
        return ResponseEntity.ok(diets);
    }

    @PostMapping
    public ResponseEntity<DietDTO> createDiet(@RequestBody DietDTO dietDTO) {
        DietDTO dietCreated = dietService.createDiet(dietDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dietCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(dietCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DietDTO> updateDiet(@PathVariable Long id, @RequestBody DietDTO dietDTO) {
        dietDTO.setId(id);
        DietDTO updatedDiet = dietService.updateDiet(dietDTO);
        return ResponseEntity.ok(updatedDiet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiet(@PathVariable Long id) {
        dietService.deleteDiet(id);
        return ResponseEntity.noContent().build();
    }
}

