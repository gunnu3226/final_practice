package com.sparta.finalpractice.food.dto;

import com.sparta.finalpractice.food.Food;
import lombok.Getter;

@Getter
public class FoodResponse {

    private Long foodId;
    private Long StoreId;
    private String foodName;

    public FoodResponse(Food food) {
        this.foodId = food.getId();
        this.StoreId = food.getStore().getId();
        this.foodName = food.getName();
    }
}
