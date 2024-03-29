package com.sparta.finalpractice.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.finalpractice.global.dto.CommonResponse;
import com.sparta.finalpractice.security.UserDetailsImpl;
import com.sparta.finalpractice.user.entity.UserRole;
import com.sparta.finalpractice.user.dto.LoginRequestDto;
import com.sparta.finalpractice.user.dto.UserResponseDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;

    public JwtLoginFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/users/login");
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response) throws AuthenticationException {
        log.info("로그인 시도");
        try {
            LoginRequestDto requestDto = new ObjectMapper()
                .readValue(request.getInputStream(), LoginRequestDto.class);

            return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                    requestDto.getEmail(),
                    requestDto.getPassword(),
                    null
                )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, FilterChain chain, Authentication authResult)
        throws IOException, ServletException {
        log.info("로그인 성공 및 JWT 생성");
        Long userId = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getId();
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getEmail();
        UserRole role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();

        String jsonResponse = new ObjectMapper().writeValueAsString(
            new CommonResponse<>(new UserResponseDto(userId, role)));

        String token = jwtUtil.createToken(userId, username, role);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, AuthenticationException failed)
        throws IOException, ServletException {
        log.info("로그인 실패");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
    }
}
