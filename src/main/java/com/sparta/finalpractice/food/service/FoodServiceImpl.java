package com.sparta.finalpractice.food.service;

import com.sparta.finalpractice.food.dto.FoodRequest;
import com.sparta.finalpractice.food.dto.FoodResponse;
import com.sparta.finalpractice.food.dto.FoodUpdateRequest;
import com.sparta.finalpractice.food.entity.Food;
import com.sparta.finalpractice.food.repository.FoodRepository;
import com.sparta.finalpractice.store.entity.Store;
import com.sparta.finalpractice.store.service.StoreService;
import com.sparta.finalpractice.user.entity.User;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final StoreService storeService;

    @Override
    @Transactional
    public FoodResponse createFood(Long storeId, FoodRequest foodrequest, User user) {
        Store store = checkOwner(storeId, user.getId());
        Food savedFood = foodRepository.save(new Food(foodrequest, store));
        return new FoodResponse(savedFood);
    }

    @Override
    @Transactional
    public FoodResponse updateFood(Long storeId, Long foodId, FoodUpdateRequest foodRequest, User user) {
        checkOwner(storeId, user.getId());
        Food findFood = findFoodById(foodId);
        findFood.update(foodRequest);
        return new FoodResponse(findFood);
    }

    @Override
    @Transactional
    public FoodResponse deleteFood(Long storeId, Long foodId, User user) {
        checkOwner(storeId, user.getId());
        Food food = findFoodById(foodId);
        food.delete();
        return new FoodResponse(food);
    }

    @Override
    public Food findFoodById(Long foodId) {
        Food findFood = foodRepository.findById(foodId).orElseThrow(
            () -> new NoSuchElementException("Food가 존재하지 않습니다.")
        );
        return findFood;
    }

    private Store checkOwner(Long storeId, Long userId) {
        Store store = storeService.findStoreById(storeId);
        if (!store.getOwner().getId().equals(userId)) {
            throw new AccessDeniedException("Store의 Owner만 접근할 수 있습니다.");
        }
        return store;
    }

}
