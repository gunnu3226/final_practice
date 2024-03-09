package com.sparta.finalpractice.food.dto;

import com.sparta.finalpractice.food.entity.Food;
import lombok.Getter;

@Getter
public class FoodResponse {

    private Long id;

    private String name;

    private String description;

    private Long price;

    public FoodResponse(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.description = food.getDescription();
        this.price = food.getPrice();
    }

    public FoodResponse(Long foodId, String name) {
        this.id = foodId;
        this.name = name;
    }
}
