package com.sparta.finalpractice.user;

import com.sparta.CommonTest;
import com.sparta.finalpractice.user.dto.SignupRequestDto;

public interface UserTest extends CommonTest {

    String ENCODED_PASSWORD = "enCodedPassword";
    SignupRequestDto SIGNUP_REQUEST_DTO = new SignupRequestDto(
        USER_EMAIL, USER_PASSWORD, USER_NICKNAME, OWNER_TOKEN);


}
