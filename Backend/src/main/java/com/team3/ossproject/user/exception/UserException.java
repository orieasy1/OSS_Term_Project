package com.team3.ossproject.user.exception;


import com.team3.ossproject.global.exception.GlobalCodeException;

public class UserException extends GlobalCodeException {
	public UserException(UserErrorCode errorCode) {
		super(errorCode);
	}
}
