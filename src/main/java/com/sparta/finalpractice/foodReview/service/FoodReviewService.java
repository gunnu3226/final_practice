package com.sparta.finalpractice.foodReview.service;

import com.sparta.finalpractice.foodReview.dto.FoodReviewDeleteResponse;
import com.sparta.finalpractice.foodReview.dto.FoodReviewRequest;
import com.sparta.finalpractice.foodReview.dto.FoodReviewResponse;
import com.sparta.finalpractice.user.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface FoodReviewService {

    /**
     * 음식 리뷰 생성
     * @param foodId 음식 ID
     * @param request 댓글내용, 평점
     * @param user 토큰 인증 유저
     * @return 댓글 생성 결과
     */
    @Transactional
    public FoodReviewResponse createFoodReview(Long foodId, FoodReviewRequest request, User user);

    /**
     * 음식 리뷰 수정
     * @param foodReviewId 음식리뷰 ID
     * @param request 수정할 댓글 내용, 평점
     * @param user 토큰 인증 유저
     * @return 댓글 수정 결과
     */
    @Transactional
    public FoodReviewResponse updateFoodReview(Long foodReviewId, FoodReviewRequest request, User user);

    /**
     * 음식 리뷰 삭제
     * @param foodReviewId 음색리뷰 ID
     * @param user 토큰 인증 유저
     * @return 댓글 삭제 시간
     */
    @Transactional
    public FoodReviewDeleteResponse deleteFoodReview(Long foodReviewId, User user);

}
