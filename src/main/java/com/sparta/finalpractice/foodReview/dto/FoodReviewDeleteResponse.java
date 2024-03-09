package com.sparta.finalpractice.foodReview.dto;

import com.sparta.finalpractice.foodReview.entity.FoodReview;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FoodReviewDeleteResponse {

    private Long id;
    private LocalDateTime deletedAt;

    public FoodReviewDeleteResponse(FoodReview foodReview) {
        this.id = foodReview.getId();
        this.deletedAt = foodReview.getDeletedAt();
    }
}
