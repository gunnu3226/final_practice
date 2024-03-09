package com.sparta.finalpractice.food.service;

import com.sparta.finalpractice.food.dto.FoodRequest;
import com.sparta.finalpractice.food.dto.FoodResponse;
import com.sparta.finalpractice.food.dto.FoodUpdateRequest;
import com.sparta.finalpractice.food.entity.Food;
import com.sparta.finalpractice.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public interface FoodService {

    @Transactional
    public FoodResponse createFood(Long storeId, FoodRequest foodrequest, User user);

    @Transactional
    public FoodResponse updateFood(Long storeId, Long foodId, FoodUpdateRequest foodRequest, User user);

    @Transactional
    public FoodResponse deleteFood(Long storeId, Long foodId, User user);

    public Food findFoodById(Long foodId);
}
