package com.sparta.finalpractice.store.dto;

import com.sparta.finalpractice.food.dto.FoodResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreListResponse {

    private Long id;
    private String name;
    private String introduce;
    private List<FoodResponse> foods;
}
