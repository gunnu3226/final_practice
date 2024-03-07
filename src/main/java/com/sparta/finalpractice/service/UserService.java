package com.sparta.finalpractice.service;

import com.sparta.finalpractice.dto.user.SignupRequestDto;
import com.sparta.finalpractice.dto.user.UserResponseDto;
import com.sparta.finalpractice.entity.User;
import com.sparta.finalpractice.entity.UserRole;
import com.sparta.finalpractice.exception.EmailExistException;
import com.sparta.finalpractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.token}")
    private String ADMIN_TOKEN;

    public UserResponseDto signup(SignupRequestDto requestDto) {
        checkDuplicationEmail(requestDto);
        UserRole userRole = validateUserRole(requestDto.getRole());
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        return new UserResponseDto(userRepository.save(
            new User(requestDto, encodedPassword, userRole)));
    }

    private void checkDuplicationEmail(SignupRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new EmailExistException("해당 email은 이미 존재합니다.");
        }
    }

    private UserRole validateUserRole(String role) {
        if(role != null && role.equals(ADMIN_TOKEN)) {
            return UserRole.ADMIN;
        }
        return UserRole.USER;
    }
}
