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

    /**
     * 음식 등록
     * @param storeId 가게 ID
     * @param foodrequest 등록할 음식 정보
     * @param user 인증 유저(가게 오너만 가능)
     * @return 등록된 음식 정보
     */
    @Transactional
    public FoodResponse createFood(Long storeId, FoodRequest foodrequest, User user);

    /**
     * 음식 정보 수정
     * @param storeId 가게 ID
     * @param foodId 음식 ID
     * @param foodRequest 수정할 음식 정보
     * @param user 인증 유저(가게 오너만 가능)
     * @return
     */
    @Transactional
    public FoodResponse updateFood(Long storeId, Long foodId, FoodUpdateRequest foodRequest, User user);

    /**
     * 음식 삭제(soft delete)
     * @param storeId 가게 ID
     * @param foodId 음식 ID
     * @param user 인증 유저(가게 오너만 가능)
     * @return
     */
    @Transactional
    public FoodResponse deleteFood(Long storeId, Long foodId, User user);

    public Food findFoodById(Long foodId);
}
