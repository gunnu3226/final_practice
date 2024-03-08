package com.sparta.finalpractice.store;

import com.sparta.finalpractice.CommonResponse;
import com.sparta.finalpractice.security.UserDetailsImpl;
import com.sparta.finalpractice.store.dto.StoreRegisterRequestDto;
import com.sparta.finalpractice.store.dto.StoreResponseDto;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<CommonResponse<StoreResponseDto>> registerStore(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody @Valid StoreRegisterRequestDto requestDto
    ) {
        StoreResponseDto responseDto = storeService.registerStore(userDetails.getId(),
            requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new CommonResponse<>(responseDto)
        );
    }

//    @GetMapping("{id}")
 //    public ResponseEntity<StoreResponseDto> getStoreDetail() {
//
//    }

//    @GetMapping
//    public ResponseEntity<List<StoreResponseDto>> getStoreList() {
//
//    }
}
