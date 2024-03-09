package com.sparta.finalpractice.user.service;

import com.sparta.finalpractice.user.dto.SignupRequestDto;
import com.sparta.finalpractice.user.dto.UserResponseDto;
import com.sparta.finalpractice.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public interface UserService {

    /**
     * 회원가입
     * @param requestDto 회원가입 정보
     * @return 가입된 회원
     */
    @Transactional
    public UserResponseDto signup(SignupRequestDto requestDto);

    public User findUserById(Long id);
}
