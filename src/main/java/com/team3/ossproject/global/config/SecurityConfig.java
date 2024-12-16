package com.team3.ossproject.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 보호 비활성화 (개발 환경에서만 적용)
                .csrf(csrf -> csrf.disable())

                // 경로별 접근 권한 설정
                .authorizeHttpRequests(auth -> auth
                        // Swagger UI 및 Swagger 관련 리소스 접근 허용
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/configuration/**"
                        ).permitAll()

                        // API 경로 접근 허용
                        .requestMatchers("/api/v1/**").permitAll()

                        // 나머지 요청은 인증 필요
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
