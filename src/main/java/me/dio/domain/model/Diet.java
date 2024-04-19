package me.dio.domain.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_diet")
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    private List<String> foods;
    private int consumedCalories;

    public Diet() {}

    public Diet(Long id, User user, List<String> foods, int consumedCalories) {
        this.id = id;
        this.user = user;
        this.foods = foods;
        this.consumedCalories = consumedCalories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

