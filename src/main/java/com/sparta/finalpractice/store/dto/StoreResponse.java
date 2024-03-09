package com.sparta.finalpractice.store.dto;

import com.sparta.finalpractice.food.dto.FoodResponse;
import com.sparta.finalpractice.store.entity.Store;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {

    private Long id;
    private String name;
    private String introduce;
    private List<FoodResponse> foods;

    public StoreResponse(Store store) {
        this.id = store.getId();
        this.name = store.getName();
        this.introduce = store.getIntroduce();
        this.foods = store.getFoods().stream()
            .map(FoodResponse::new)
            .collect(Collectors.toList());
    }
}
