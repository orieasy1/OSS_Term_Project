package com.team3.ossproject.auth.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateAccessTokenResponse {
    private String accessToken;
}
