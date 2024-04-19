package me.dio.domain.repository;

import me.dio.domain.model.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepository extends JpaRepository<Diet, Long> {
    
}
