package com.sanchat.api.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    /**
     * JWT 검증 필터 수행
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        log.info("Authorization header: {}", authorizationHeader);

        if (authorizationHeader == null) {
            log.warn("Authorization header is null.");
        }

        // JWT 헤더가 있을 경우
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            // JWT 유효성 검증
            if (jwtUtil.isValidToken(token)) {
                String id = jwtUtil.getId(token);
                log.info("Token is valid. Retrieved userId: {}", id);

                // 유저와 토큰 일치 시 UserDetails 생성
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(id);
                log.info("Loaded user details: {}", userDetails);

                if (userDetails != null) {
                    // UserDetails, Password, Role -> 접근 권한 인증 Token 생성
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    // 현재 Request의 Security Context에 접근 권한 설정
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } else {
                log.warn("Invalid JWT token: {}", token);
            }
        }

        filterChain.doFilter(request, response); // 다음 필터로 넘김
    }
}
