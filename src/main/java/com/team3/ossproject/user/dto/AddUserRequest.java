package com.team3.ossproject.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Schema(description = "User Information DTO")
public class AddUserRequest {
    private String email;
    private String password;
    private String nickname;
}
