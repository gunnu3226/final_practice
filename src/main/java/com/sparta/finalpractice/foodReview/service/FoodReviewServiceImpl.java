package com.sparta.finalpractice.foodReview.service;

import com.sparta.finalpractice.food.entity.Food;
import com.sparta.finalpractice.food.service.FoodService;
import com.sparta.finalpractice.foodReview.dto.FoodReviewDeleteResponse;
import com.sparta.finalpractice.foodReview.dto.FoodReviewRequest;
import com.sparta.finalpractice.foodReview.dto.FoodReviewResponse;
import com.sparta.finalpractice.foodReview.entity.FoodReview;
import com.sparta.finalpractice.foodReview.repository.FoodReviewRepository;
import com.sparta.finalpractice.foodReview.service.FoodReviewService;
import com.sparta.finalpractice.user.entity.User;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodReviewServiceImpl implements FoodReviewService {

    private final FoodReviewRepository foodReviewRepository;
    private final FoodService foodService;
    
    @Transactional
    @Override
    public FoodReviewResponse createFoodReview(Long foodId, FoodReviewRequest request,
        User user) {
        Food food = foodService.findFoodById(foodId);
        FoodReview savedFoodReview = foodReviewRepository.save(
            new FoodReview(request, food, user));
        food.addReviewCount();
        return new FoodReviewResponse(savedFoodReview);
    }

    @Transactional
    @Override
    public FoodReviewResponse updateFoodReview(Long FoodReviewId, FoodReviewRequest request,
        User user) {
        FoodReview foodReview = findFoodReviewById(FoodReviewId);
        checkCommentUser(FoodReviewId, user.getId());
        foodReview.update(request);
        return new FoodReviewResponse(foodReview);
    }

    @Transactional
    @Override
    public FoodReviewDeleteResponse deleteFoodReview(Long FoodReviewId, User user) {
        FoodReview foodReview = findFoodReviewById(FoodReviewId);
        checkCommentUser(FoodReviewId, user.getId());
        foodReview.delete();
        foodReview.getFood().subtractReviewCount();
        return new FoodReviewDeleteResponse(foodReview);
    }

    public FoodReview findFoodReviewById(Long id) {
        return foodReviewRepository.findById(id).orElseThrow(
            () -> new NoSuchElementException("ID : " + id + "인 FoodReview는 존재하지 않습니다.")
        );
    }
    
    public void checkCommentUser(Long commentUserId, Long userId) {
        if(!commentUserId.equals(userId)) {
            throw new AccessDeniedException("댓글 작성자에게만 권한이 있습니다.");
        }
    }
}
