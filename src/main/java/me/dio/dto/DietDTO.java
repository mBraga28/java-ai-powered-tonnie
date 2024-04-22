package me.dio.dto;

import me.dio.domain.model.Diet;

import java.util.List;

public class DietDTO {

    private Long id;

    private Long userId;

    private List<String> foods;
    private int consumedCalories;

    public DietDTO() {}

    public DietDTO(Long id, Long userId, List<String> foods, int consumedCalories) {
        this.id = id;
        this.userId = userId;
        this.foods = foods;
        this.consumedCalories = consumedCalories;
    }

    public DietDTO(Diet entity) {
        id = entity.getId();
        userId = entity.getUser().getId();
        foods = entity.getFoods();
        consumedCalories = entity.getConsumedCalories();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<String> getFoods() {
        return foods;
    }

    public void setFoods(List<String> foods) {
        this.foods = foods;
    }

    public int getConsumedCalories() {
        return consumedCalories;
    }

    public void setConsumedCalories(int consumedCalories) {
        this.consumedCalories = consumedCalories;
    }

    
}

