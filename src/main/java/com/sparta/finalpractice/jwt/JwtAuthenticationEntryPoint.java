package com.sparta.finalpractice.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.finalpractice.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException)
        throws IOException {
        String jsonResponse = new ObjectMapper().writeValueAsString(
            new ErrorResponse("유효하지 않은 토큰 입니다."));
        log.error("유효하지 않은 토큰 접근");
        // 유효한 자격증명을 제공하지 않고 접근하려 할때 401
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(jsonResponse);
    }
}

