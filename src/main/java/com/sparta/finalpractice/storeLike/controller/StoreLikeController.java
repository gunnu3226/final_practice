package com.sparta.finalpractice.storeLike.controller;

import com.sparta.finalpractice.global.dto.CommonResponse;
import com.sparta.finalpractice.security.UserDetailsImpl;
import com.sparta.finalpractice.storeLike.service.StoreLikeService;
import com.sparta.finalpractice.storeLike.dto.StoreLikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores/{storeId}/like")
public class StoreLikeController {

    private final StoreLikeService storeLikeService;

//    @PostMapping
//    @Secured("ROLE_USER")
//    public ResponseEntity<CommonResponse<StoreLikeResponse>> doStoreLike(
//        @PathVariable("storeId") Long storeId,
//        @AuthenticationPrincipal UserDetailsImpl userDetails
//    ) {
//        storeLikeService.storeLikeRedis(
//            userDetails.getUser(), storeId);
//        return ResponseEntity.status(HttpStatus.OK).body(
//            new CommonResponse<>());
//    }

//    @DeleteMapping
//    @Secured("ROLE_USER")
//    public ResponseEntity<CommonResponse<StoreLikeResponse>> cancelStoreLike(
//        @PathVariable("storeId") Long storeId,
//        @AuthenticationPrincipal UserDetailsImpl userDetails
//    ) {
//        StoreLikeResponse response = storeLikeService.cancelStoreLike(
//            userDetails.getUser(), storeId);
//        return ResponseEntity.status(HttpStatus.OK).body(
//            new CommonResponse<>(response));
//    }
}

