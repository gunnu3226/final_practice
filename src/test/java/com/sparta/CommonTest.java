package com.sparta;

import com.sparta.finalpractice.user.entity.User;
import com.sparta.finalpractice.user.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface CommonTest {

    String ANOTHER_PREFIX = "another-";
    Long OWNER_USER_ID = 1L;
    Long ANOTHER_ID = 2L;
    String USER_EMAIL = "owner@emai.com";
    String USER_NICKNAME = "owner";
    String USER_PASSWORD = "password";
    String USER_ENCODED_PASSWORD = "enCodedPassword";
    String OWNER_TOKEN = "ownerToken";
    String ADMIN_TOKEN = "adminToken";
    UserRole ROLE_OWNER = UserRole.OWNER;
    String ROLE_OWNER_STRING = UserRole.OWNER.getAuthority();

    User OWNER_USER  = User.builder()
        .id(OWNER_USER_ID)
        .email(USER_EMAIL)
        .password(USER_ENCODED_PASSWORD)
        .nickname(USER_NICKNAME)
        .role(ROLE_OWNER)
        .build();


}
