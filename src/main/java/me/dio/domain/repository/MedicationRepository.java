package me.dio.domain.repository;

import me.dio.domain.model.Medication;
import me.dio.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

    List<Medication> findByUser(User user);
}
