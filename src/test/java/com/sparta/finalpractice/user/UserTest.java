package com.sparta.finalpractice.user;

import com.sparta.CommonTest;
import com.sparta.finalpractice.security.UserDetailsImpl;
import com.sparta.finalpractice.user.dto.SignupRequestDto;
import com.sparta.finalpractice.user.dto.UserResponseDto;

public interface UserTest extends CommonTest {

    String ENCODED_PASSWORD = "enCodedPassword";
    SignupRequestDto TEST_SIGNUP_REQUEST_DTO = new SignupRequestDto(
        TEST_USER_EMAIL, TEST_USER_PASSWORD, TEST_USER_NICKNAME, TEST_OWNER_TOKEN);

    UserResponseDto TEST_USER_RESPONSE = new UserResponseDto(TEST_OWNER_USER);
    UserDetailsImpl TEST_USER_DETAILS = new UserDetailsImpl(TEST_OWNER_USER);
}
