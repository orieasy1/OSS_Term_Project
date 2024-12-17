package com.team3.ossproject.global.dto;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private final int code;
    private final String message;
    private final T data;

    private ApiResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "success", data);
    }
}
