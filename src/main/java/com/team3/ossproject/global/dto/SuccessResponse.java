package com.team3.ossproject.global.dto;

import lombok.Getter;

@Getter
public class SuccessResponse<T> {
    private final int code;
    private final String message;
    private final T data;

    private SuccessResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> SuccessResponse<T> success(T data) {
        return new SuccessResponse<>(200, "success", data);
    }
}
