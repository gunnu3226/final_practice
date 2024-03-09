package com.sparta.finalpractice.food.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FoodUpdateRequest {

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private Long price;
}
