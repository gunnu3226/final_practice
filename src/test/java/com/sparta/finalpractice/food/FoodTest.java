package com.sparta.finalpractice.food;

import com.sparta.CommonTest;
import com.sparta.finalpractice.food.entity.Food;
import com.sparta.finalpractice.store.StoreTest;
import java.util.ArrayList;
import java.util.List;

public interface FoodTest {

    Long TEST_FOOD_ID_1 = 1L;
    Long TEST_FOOD_ID_2 = 2L;
    String TEST_FOOD_NAME_1 = "Food1";
    String TEST_FOOD_NAME_2 = "Food2";
    String TEST_FOOD_DESCRIPTION_1 = "food description1";
    String TEST_FOOD_DESCRIPTION_2 = "food description2";
    Long TEST_FOOD_PRICE_1 = 10000L;
    Long TEST_FOOD_PRICE_2 = 20000L;

    Food TEST_FOOD_1 = Food.builder()
        .id(TEST_FOOD_ID_1)
        .name(TEST_FOOD_NAME_1)
        .description(TEST_FOOD_DESCRIPTION_1)
        .price(TEST_FOOD_PRICE_1)
        .build();

    Food TEST_FOOD_2 = Food.builder()
        .id(TEST_FOOD_ID_2)
        .name(TEST_FOOD_NAME_2)
        .description(TEST_FOOD_DESCRIPTION_2)
        .price(TEST_FOOD_PRICE_2)
        .build();

    List<Food> TEST_FOOD_LIST = new ArrayList<>(List.of(new Food[]{TEST_FOOD_1, TEST_FOOD_2}));
}
