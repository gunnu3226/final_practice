package com.sparta.finalpractice.user.dto;

import com.sparta.finalpractice.user.entity.User;
import com.sparta.finalpractice.user.entity.UserRole;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long userId;
    private String userRole;

    public UserResponseDto(Long userId, UserRole role) {
        this.userId = userId;
        this.userRole = role.getAuthority();
    }

    public UserResponseDto(User user) {
        this.userId = user.getId();
        this.userRole = user.getRole().getAuthority();
    }
}
