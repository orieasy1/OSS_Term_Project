package com.team3.ossproject.auth.exception;

import com.team3.ossproject.global.exception.GlobalCodeException;
import lombok.Getter;

@Getter
public class AuthException extends GlobalCodeException {

	public AuthException(AuthErrorCode errorCode) {
		super(errorCode);
	}
}

