package com.sparta.finalpractice.foodReview.dto;

import com.sparta.finalpractice.foodReview.entity.FoodReview;
import lombok.Getter;

@Getter
public class FoodReviewResponse {

    private Long id;
    private String Comment;
    private int score;

    public FoodReviewResponse(FoodReview foodReview) {
        this.id = foodReview.getId();
        this.Comment = foodReview.getComment();
        this.score = foodReview.getScore();
    }
}
