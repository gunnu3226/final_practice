package com.sparta.finalpractice.foodReview.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class FoodReviewRequest {

    private String Comment;

    @Range(min = 0, max = 10)
    private int score;
}
