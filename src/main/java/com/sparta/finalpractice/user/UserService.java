package com.sparta.finalpractice.user;

import com.sparta.finalpractice.user.dto.SignupRequestDto;
import com.sparta.finalpractice.user.dto.UserResponseDto;
import com.sparta.finalpractice.exception.user.EmailExistException;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.token}")
    private String ADMIN_TOKEN;

    @Value("${owner.token}")
    private String OWNER_TOKEN;

    @Transactional
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
        if(role != null) {
            if(role.equals(ADMIN_TOKEN)) {
                return UserRole.ADMIN;
            } else if (role.equals(OWNER_TOKEN)) {
                return UserRole.OWNER;
            }
        }
        return UserRole.USER;
    }

    public User findOneUser(Long id) {
        return userRepository.findById(id).orElseThrow(
            () -> new NoSuchElementException()
        );
    }
}
