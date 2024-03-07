package com.sparta.finalpractice.controller;

import com.sparta.finalpractice.dto.CommonResponse;
import com.sparta.finalpractice.dto.user.SignupRequestDto;
import com.sparta.finalpractice.dto.user.UserResponseDto;
import com.sparta.finalpractice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CommonResponse> signup(
            @RequestBody @Valid SignupRequestDto requestDto
        ){
        UserResponseDto responseDto = userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            CommonResponse.builder()
                .data(responseDto)
                .build()
        );
    }
}
