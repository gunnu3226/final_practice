package com.sparta.finalpractice.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.sparta.finalpractice.user.UserTest;
import com.sparta.finalpractice.user.dto.SignupRequestDto;
import com.sparta.finalpractice.user.dto.UserResponseDto;
import com.sparta.finalpractice.user.entity.User;
import com.sparta.finalpractice.user.repository.UserRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceTest implements UserTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @DisplayName("회원가입")
    @Test
    void signup() {
        //given
        SignupRequestDto requestDto = SIGNUP_REQUEST_DTO;
        given(userRepository.save(any(User.class))).willReturn(OWNER_USER);
        given(passwordEncoder.encode(requestDto.getPassword())).willReturn(ENCODED_PASSWORD);

        //when
        UserResponseDto response = userService.signup(requestDto);

        //then
        assertThat(OWNER_USER_ID).isEqualTo(response.getUserId());
        assertThat(ROLE_OWNER_STRING).isEqualTo(response.getUserRole());
    }

    @Nested
    @DisplayName("회원ID로조회")
    class findUserById {

        @DisplayName("조회성공")
        @Test
        void findUserById_success() {
            //given
            given(userRepository.findById(OWNER_USER_ID)).willReturn(
                Optional.ofNullable(OWNER_USER));

            //when
            User findUser = userService.findUserById(OWNER_USER_ID);

            //then
            assertThat(OWNER_USER_ID).isEqualTo(findUser.getId());
            assertThat(USER_EMAIL).isEqualTo(findUser.getEmail());
            assertThat(USER_ENCODED_PASSWORD).isEqualTo(findUser.getPassword());
            assertThat(USER_NICKNAME).isEqualTo(findUser.getNickname());
        }

        @DisplayName("조회실패_유저없음")
        @Test
        void findUserById_fail() {
            //given
            given(userRepository.findById(ANOTHER_ID)).willThrow(NoSuchElementException.class);

            //when, then
            assertThrows(NoSuchElementException.class,
                () -> userService.findUserById(ANOTHER_ID));
        }
    }
}