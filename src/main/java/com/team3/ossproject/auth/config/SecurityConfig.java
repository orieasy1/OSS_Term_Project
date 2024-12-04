package com.team3.ossproject.auth.config;


import com.team3.ossproject.auth.jwt.TokenAuthenticationFilter;
import com.team3.ossproject.auth.jwt.TokenProvider;
import com.team3.ossproject.auth.jwt.refreshtoken.RefreshTokenRepository;
import com.team3.ossproject.auth.oauth2.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.team3.ossproject.auth.oauth2.OAuth2SuccessHandler;
import com.team3.ossproject.auth.oauth2.OAuth2UserCustomService;
import com.team3.ossproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2UserCustomService oAuth2UserCustomService;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 설정
        http.securityContext(securityContext ->
                securityContext.requireExplicitSave(false) // SecurityContext를 명시적으로 저장하지 않음
        );

        // 세션 관리 Stateless로 설정
        http.securityContext(securityContext ->
                securityContext.requireExplicitSave(false)
        ).sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // HTTP Basic 및 Form 로그인 비활성화
        http.httpBasic(httpBasic -> {}).formLogin(formLogin -> {});

        // URL별 권한 설정
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/error").permitAll() // Swagger 및 공개 URL 허용
                .requestMatchers("/oauth2/**").permitAll() // OAuth2 로그인 허용
                .requestMatchers("/api/**").authenticated() // API 경로는 인증 필요
                .anyRequest().permitAll()
        );

        // JWT 필터 추가
        http.addFilterBefore(new TokenAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

        // OAuth2 로그인 설정
        http.oauth2Login(oauth2 -> oauth2
                .loginPage("/login") // 사용자 정의 로그인 페이지
                .authorizationEndpoint(authEndpoint -> authEndpoint
                        .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository()))
                .successHandler(oAuth2SuccessHandler())
                .userInfoEndpoint(userInfo -> userInfo
                        .userService(oAuth2UserCustomService))
        );

        // 로그아웃 설정
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID"));

        // API 인증 실패 시 예외 처리
        http.exceptionHandling(exception ->
                exception.defaultAuthenticationEntryPointFor(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                        new AntPathRequestMatcher("/api/**"))
        );

        return http.build();
    }

    @Bean
    public OAuth2SuccessHandler oAuth2SuccessHandler() {
        return new OAuth2SuccessHandler(tokenProvider,
                refreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository(),
                userService
        );
    }

    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }
}
