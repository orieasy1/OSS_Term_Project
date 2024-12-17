package com.team3.ossproject.global.exception;

import com.team3.ossproject.global.dto.ErrorReason;

public interface BaseErrorCode {
	public ErrorReason getErrorReason();

	String getExplainError() throws NoSuchFieldException;
}
