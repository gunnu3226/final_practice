package com.sparta.finalpractice.foodReview.repository;

import com.sparta.finalpractice.foodReview.entity.FoodReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodReviewRepository extends JpaRepository<FoodReview, Long> {

}
