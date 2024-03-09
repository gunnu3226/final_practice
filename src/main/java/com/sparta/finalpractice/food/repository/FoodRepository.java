package com.sparta.finalpractice.food.repository;

import com.sparta.finalpractice.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {

}
