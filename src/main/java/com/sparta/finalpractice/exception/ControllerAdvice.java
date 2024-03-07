package com.sparta.finalpractice.exception;

import com.sparta.finalpractice.CommonResponse;
import com.sparta.finalpractice.exception.user.EmailExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<CommonResponse> emailExistException(EmailExistException e) {
        log.error("중복 이메일 에러");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            CommonResponse.builder()
                .data(e.getMessage())
                .build()
        );
    }
}
