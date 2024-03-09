package com.sparta.finalpractice.food.controller;

import com.sparta.finalpractice.food.service.FoodService;
import com.sparta.finalpractice.global.dto.CommonResponse;
import com.sparta.finalpractice.food.dto.FoodRequest;
import com.sparta.finalpractice.food.dto.FoodResponse;
import com.sparta.finalpractice.food.dto.FoodUpdateRequest;
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

@Secured("ROLE_OWNER")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores/{storeId}/food")
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    public ResponseEntity<CommonResponse<FoodResponse>> createFood(
        @PathVariable("storeId") Long storeId,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody FoodRequest foodrequest
    ) {
        FoodResponse response = foodService.createFood(storeId, foodrequest, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new CommonResponse<>(response)
        );
    }

    @PutMapping("/{foodId}")
    public ResponseEntity<CommonResponse<FoodResponse>> updateFood(
        @PathVariable("storeId") Long storeId,
        @PathVariable("foodId") Long foodId,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody FoodUpdateRequest request
    ) {
        FoodResponse response = foodService.updateFood(storeId, foodId, request,
            userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(
            new CommonResponse<>(response)
        );
    }

    @DeleteMapping("/{foodId}")
    public ResponseEntity<CommonResponse<FoodResponse>> deleteFood(
        @PathVariable("storeId") Long storeId,
        @PathVariable("foodId") Long foodId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        FoodResponse response = foodService.deleteFood(storeId, foodId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(
            new CommonResponse<>(response)
        );
    }
}
