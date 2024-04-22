package me.dio.domain.repository;

import me.dio.domain.model.Activity;
import me.dio.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByUser(User user);
}
