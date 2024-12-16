package com.team3.ossproject.global.exception;

import com.team3.ossproject.global.dto.ErrorReason;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobalCodeException extends RuntimeException {
	private BaseErrorCode errorCode;

	public ErrorReason getErrorReason() {
		return this.errorCode.getErrorReason();
	}
}
