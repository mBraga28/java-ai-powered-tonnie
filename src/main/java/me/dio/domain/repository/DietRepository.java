package me.dio.domain.repository;

import me.dio.domain.model.Diet;
import me.dio.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DietRepository extends JpaRepository<Diet, Long> {

    List<Diet> findByUser(User user);
}
