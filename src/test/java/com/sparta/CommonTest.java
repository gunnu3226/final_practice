package com.sparta;

import com.sparta.finalpractice.user.entity.User;
import com.sparta.finalpractice.user.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface CommonTest {


    String ANOTHER_PREFIX = "another-";
    Long TEST_OWNER_USER_ID = 1L;
    Long TEST_ANOTHER_ID = 2L;
    String TEST_USER_EMAIL = "owner@emai.com";
    String TEST_USER_NICKNAME = "owner";
    String TEST_USER_PASSWORD = "password";
    String TEST_USER_ENCODED_PASSWORD = "enCodedPassword";
    String TEST_OWNER_TOKEN = "ownerToken";
    String TEST_ADMIN_TOKEN = "adminToken";
    UserRole TEST_ROLE_OWNER = UserRole.OWNER;
    String TEST_ROLE_OWNER_STRING = UserRole.OWNER.getAuthority();

    User TEST_OWNER_USER  = User.builder()
        .id(TEST_OWNER_USER_ID)
        .email(TEST_USER_EMAIL)
        .password(TEST_USER_ENCODED_PASSWORD)
        .nickname(TEST_USER_NICKNAME)
        .role(TEST_ROLE_OWNER)
        .build();


}
