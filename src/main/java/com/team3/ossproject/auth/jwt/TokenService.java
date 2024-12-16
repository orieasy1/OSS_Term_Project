package com.team3.ossproject.auth.jwt;


import com.team3.ossproject.auth.exception.AuthErrorCode;
import com.team3.ossproject.auth.exception.AuthException;
import com.team3.ossproject.auth.jwt.refreshtoken.RefreshTokenService;
import com.team3.ossproject.user.domain.User;
import com.team3.ossproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사
        if (!tokenProvider.validToken(refreshToken)) {
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        }

        // RefreshToken에서 User 엔티티 추출
        User user = refreshTokenService.findByRefreshToken(refreshToken).getUser();

        // 새 AccessToken 생성
        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }

}