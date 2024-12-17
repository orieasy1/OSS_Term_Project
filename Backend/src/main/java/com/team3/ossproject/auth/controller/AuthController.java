package com.team3.ossproject.auth.controller;

import com.team3.ossproject.auth.docs.LoginAuthExceptionDocs;
import com.team3.ossproject.auth.docs.RefreshAuthExceptionDocs;
import com.team3.ossproject.auth.jwt.TokenService;
import com.team3.ossproject.auth.jwt.dto.CreateAccessTokenRequest;
import com.team3.ossproject.auth.jwt.dto.CreateAccessTokenResponse;
import com.team3.ossproject.global.annotation.ApiErrorExceptionsExample;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Token", description = "Token API")
@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final TokenService tokenService;

    @GetMapping("/oauth2/google")
    @Operation(summary = "Google Login", description = "[View] Display the Google login page for users")
    @ApiErrorExceptionsExample(LoginAuthExceptionDocs.class)
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/api/v1/auth/oauth2/google";

    }

    @PostMapping("/token")
    @Operation(summary = "Update AccessToken", description = "Update AccessToken by Refresh Token")
    @ApiErrorExceptionsExample(RefreshAuthExceptionDocs.class)
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(
            @RequestBody CreateAccessTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponse(newAccessToken));
    }
}
