package com.sparta.finalpractice.store.controller;

import com.sparta.finalpractice.global.dto.CommonResponse;
import com.sparta.finalpractice.security.UserDetailsImpl;
import com.sparta.finalpractice.store.service.StoreService;
import com.sparta.finalpractice.store.dto.StoreRegisterRequest;
import com.sparta.finalpractice.store.dto.StoreResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    @Secured("ROLE_OWNER")
    public ResponseEntity<CommonResponse<StoreResponse>> registerStore(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody @Valid StoreRegisterRequest requestDto
    ) {
        StoreResponse responseDto = storeService.registerStore(userDetails.getUser(),
            requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new CommonResponse<>(responseDto)
        );
    }

    @GetMapping("/jpa")
    public ResponseEntity<CommonResponse<Page>> selectTotalStoreList(Pageable pageable) {
        Page response = storeService.selectTotalStoreList(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(
            new CommonResponse<>(response)
        );
    }

    @GetMapping("/query")
    public ResponseEntity<CommonResponse<Page>> selectTotalStoreListQuery(Pageable pageable) {
        Page response = storeService.selectTotalStoreListQuery(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(
            new CommonResponse<>(response)
        );
    }

    @GetMapping("/v1/{storeId}")
    public ResponseEntity<CommonResponse<StoreResponse>> selectTotalStoreListQuery(
        @PathVariable("storeId") Long storeId
    ) {
        StoreResponse response = storeService.selectOneStoreByIdQuery(storeId);
        return ResponseEntity.status(HttpStatus.OK).body(
            new CommonResponse<>(response)
        );
    }

    @GetMapping("/v2/{storeId}")
    public ResponseEntity<CommonResponse<StoreResponse>> selectOneStoreByIdLimitFoodQuery(
        @PathVariable("storeId") Long storeId
    ) {
        StoreResponse response = storeService.selectOneStoreByIdLimitFoodQuery(storeId);
        return ResponseEntity.status(HttpStatus.OK).body(
            new CommonResponse<>(response)
        );
    }
}
