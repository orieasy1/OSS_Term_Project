package com.team3.ossproject.auth.docs;

import com.team3.ossproject.global.annotation.ExceptionDoc;
import com.team3.ossproject.global.annotation.ExplainError;
import com.team3.ossproject.global.exception.GlobalCodeException;
import com.team3.ossproject.global.exception.GlobalErrorCode;
import com.team3.ossproject.global.exception.GlobalException;
import com.team3.ossproject.global.interfaces.SwaggerExampleExceptions;

@ExceptionDoc
public class LoginAuthExceptionDocs implements SwaggerExampleExceptions {
	@ExplainError("로그인 서버에 보내는 요청에 실패한 경우 발생합니다.")
	public GlobalCodeException 서버_요청_오류 = new GlobalException(GlobalErrorCode.OTHER_SERVER_BAD_REQUEST);

	@ExplainError("구글 서버에 보낸 토큰이 만료된 경우 발생합니다.")
	public GlobalCodeException 서버_토큰_만료 = new GlobalException(GlobalErrorCode.OTHER_SERVER_EXPIRED_TOKEN);
}
