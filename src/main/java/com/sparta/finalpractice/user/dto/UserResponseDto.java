package com.sparta.finalpractice.user.dto;

import com.sparta.finalpractice.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class UserResponseDto {

    private Long userId;

    public UserResponseDto(Long userId) {
        this.userId = userId;
    }

    public UserResponseDto(User user) {
        this.userId = user.getId();
    }
}
