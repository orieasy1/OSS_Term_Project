package com.team3.ossproject.auth.jwt.refreshtoken;

import com.team3.ossproject.auth.exception.AuthErrorCode;
import com.team3.ossproject.auth.exception.AuthException;
import com.team3.ossproject.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new AuthException(AuthErrorCode.INVALID_TOKEN));
    }

    public RefreshToken findByUser(User user) {
        return refreshTokenRepository.findByUser(user)
                .orElseThrow(() -> new AuthException(AuthErrorCode.TOKEN_NOT_FOUND));
    }

    public RefreshToken saveOrUpdate(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    public void delete(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }
}
