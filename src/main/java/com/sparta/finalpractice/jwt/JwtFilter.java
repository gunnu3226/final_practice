package com.sparta.finalpractice.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.finalpractice.global.dto.ErrorResponse;
import com.sparta.finalpractice.security.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j(topic = "JWT 검증 및 인가")
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.getJwtFromHeader(request);

        if (StringUtils.hasText(token)) {
            try {
                Authentication authentication = jwtUtil.createAuthentication(token);
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);
            } catch (ExpiredJwtException e) {
                jwtErrorResponse(response, "Expired JWT token, 만료된 JWT token 입니다.");
                return;
            } catch (SecurityException | MalformedJwtException | SignatureException e) {
                jwtErrorResponse(response, "Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
                return;
            } catch (UnsupportedJwtException e) {
                jwtErrorResponse(response, "Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
                return;
            } catch (IllegalArgumentException e) {
                jwtErrorResponse(response, "JWT claims is empty, 잘못된 JWT 토큰 입니다.");
                return;
            }
        } else {
            // 인증정보가 존재하지 않을때
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json; charset=UTF-8");
        }
        filterChain.doFilter(request, response);
    }

    public void jwtErrorResponse(HttpServletResponse response, String message) throws IOException {
        log.error(message);
        String jsonResponse = new ObjectMapper().writeValueAsString(
            new ErrorResponse(message));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
