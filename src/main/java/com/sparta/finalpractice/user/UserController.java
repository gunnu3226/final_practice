package com.sparta.finalpractice.user;

import com.sparta.finalpractice.CommonResponse;
import com.sparta.finalpractice.user.dto.SignupRequestDto;
import com.sparta.finalpractice.user.dto.UserResponseDto;
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
    public ResponseEntity<CommonResponse<UserResponseDto>> signup(
        @RequestBody @Valid SignupRequestDto requestDto
    ) {
        UserResponseDto responseDto = userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new CommonResponse(requestDto)
        );
    }
}
