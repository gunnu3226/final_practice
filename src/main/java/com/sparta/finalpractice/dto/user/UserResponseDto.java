package com.sparta.finalpractice.dto.user;

import com.sparta.finalpractice.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long userId;

    public UserResponseDto(User user) {
        this.userId = user.getId();
    }
}
