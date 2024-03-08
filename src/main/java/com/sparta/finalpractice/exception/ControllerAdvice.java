package com.sparta.finalpractice.exception;

import com.sparta.finalpractice.exception.user.EmailExistException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<ErrorResponse> emailExistException(EmailExistException e) {
        log.error("중복 이메일 에러");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ErrorResponse(e.getMessage())
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> accessDeniedException() {
        log.error("접근 권한 불일치 에러");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            new ErrorResponse("접근 권한 불일치 에러")
        );
    }
}
