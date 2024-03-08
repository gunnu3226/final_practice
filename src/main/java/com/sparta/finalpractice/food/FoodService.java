package com.sparta.finalpractice.food;

import com.sparta.finalpractice.food.dto.FoodRequest;
import com.sparta.finalpractice.food.dto.FoodResponse;
import com.sparta.finalpractice.store.Store;
import com.sparta.finalpractice.store.StoreService;
import com.sparta.finalpractice.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final StoreService storeService;

    @Transactional
    public FoodResponse createFood(Long storeId, FoodRequest foodrequest, User user) {
        Store store = storeService.findStoreById(storeId);
        if(store.getOwner().getId().equals(user.getId())) {
            Food savedFood = foodRepository.save(new Food(foodrequest, store));
            return new FoodResponse(savedFood);
        }
        throw new AccessDeniedException("Store의 Owner만 메뉴를 추가할 수 있습니다.");
    }
}
