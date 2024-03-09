package com.sparta.finalpractice.foodReview.entity;

import com.sparta.finalpractice.global.entity.TimeStamp;
import com.sparta.finalpractice.food.entity.Food;
import com.sparta.finalpractice.foodReview.dto.FoodReviewRequest;
import com.sparta.finalpractice.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "FOODCOMMENT_TB")
public class FoodReview extends TimeStamp {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public FoodReview(FoodReviewRequest request, Food food, User user) {
        this.comment = request.getComment();
        this.score = request.getScore();
        this.food = food;
        this.user = user;
    }

    public void update(FoodReviewRequest request) {
        this.comment = request.getComment();
        this.score = request.getScore();
    }
}
