package com.sparta.finalpractice.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class SignupRequestDto {

    @NotNull
    @Email
    private String email;

    private String password;

    private String nickname;

    private String role;

}
