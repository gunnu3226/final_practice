package com.sparta.finalpractice.food;

import com.sparta.finalpractice.CommonResponse;
import com.sparta.finalpractice.food.dto.FoodRequest;
import com.sparta.finalpractice.food.dto.FoodResponse;
import com.sparta.finalpractice.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores/{storeId}/food")
public class FoodController {

    private final FoodService foodService;

    @Secured("ROLE_OWNER")
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
}
