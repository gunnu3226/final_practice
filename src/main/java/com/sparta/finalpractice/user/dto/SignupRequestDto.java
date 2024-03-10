package com.sparta.finalpractice.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequestDto {

    @NotNull
    @Email
    private String email;

    private String password;

    private String nickname;

    private String role;

}
