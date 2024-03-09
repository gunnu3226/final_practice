package com.sparta.finalpractice.foodReview;

import com.sparta.finalpractice.global.dto.CommonResponse;
import com.sparta.finalpractice.foodReview.dto.FoodReviewDeleteResponse;
import com.sparta.finalpractice.foodReview.dto.FoodReviewRequest;
import com.sparta.finalpractice.foodReview.dto.FoodReviewResponse;
import com.sparta.finalpractice.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Secured("ROLE_USER")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store/food/{foodId}/foodcomment")
public class FoodReviewController {

    private final FoodReviewService foodCommentService;

    @PostMapping
    public ResponseEntity<CommonResponse<FoodReviewResponse>> createFoodComment(
        @PathVariable("foodId") Long foodId,
        @RequestBody FoodReviewRequest request,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        FoodReviewResponse response = foodCommentService.createFoodReview(foodId, request,
            userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(
            new CommonResponse<>(response)
        );
    }

    @PutMapping("/{foodCommentId}")
    public ResponseEntity<CommonResponse<FoodReviewResponse>> updateFoodComment(
        @PathVariable("foodCommentId") Long foodCommentId,
        @RequestBody FoodReviewRequest request,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        FoodReviewResponse response = foodCommentService.updateFoodReview(foodCommentId, request,
            userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(
            new CommonResponse<>(response)
        );
    }

    @Secured({"ROLE_USER, ROLE_ADMIN"})
    @DeleteMapping("/{foodCommentId}")
    public ResponseEntity<CommonResponse<FoodReviewDeleteResponse>> deleteFoodComment(
        @PathVariable("foodCommentId") Long foodCommentId,
        @RequestBody FoodReviewRequest request,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        FoodReviewDeleteResponse response = foodCommentService.deleteFoodReview(foodCommentId,
            userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(
            new CommonResponse<>(response)
        );
    }
}
