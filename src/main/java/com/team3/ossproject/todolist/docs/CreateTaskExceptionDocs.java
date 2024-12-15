package com.team3.ossproject.todolist.docs;

import com.team3.ossproject.auth.exception.AuthErrorCode;
import com.team3.ossproject.auth.exception.AuthException;
import com.team3.ossproject.global.annotation.ExceptionDoc;
import com.team3.ossproject.global.annotation.ExplainError;
import com.team3.ossproject.global.exception.GlobalCodeException;
import com.team3.ossproject.global.interfaces.SwaggerExampleExceptions;
import com.team3.ossproject.todolist.exception.TaskErrorCode;
import com.team3.ossproject.todolist.exception.TaskException;
import com.team3.ossproject.user.exception.UserErrorCode;
import com.team3.ossproject.user.exception.UserException;

@ExceptionDoc
public class CreateTaskExceptionDocs implements SwaggerExampleExceptions {

	@ExplainError
	public GlobalCodeException 토큰_만료 = new AuthException(AuthErrorCode.TOKEN_EXPIRED);

	@ExplainError
	public GlobalCodeException 토큰_없음 = new AuthException(AuthErrorCode.TOKEN_NOT_FOUND);

	@ExplainError
	public GlobalCodeException 토큰_유효하지_않음 = new AuthException(AuthErrorCode.INVALID_TOKEN);

	@ExplainError("Task에 대한 사용자 권한이 없을 경우 발생하는 에러입니다")
	public GlobalCodeException 사용자_권한_없음 = new UserException(UserErrorCode.INVALID_USER);

	@ExplainError("type 값이 유효하지 않을 경우 발생하는 에러입니다")
	public GlobalCodeException 잘못된_타입 = new TaskException(TaskErrorCode.INVALID_TYPE);

	@ExplainError("priority 값이 유효하지 않을 경우 발생하는 에러입니다")
	public GlobalCodeException 잘못된_우선순위 = new TaskException(TaskErrorCode.INVALID_PRIORITY);

	@ExplainError("week 값이 유효하지 않을 경우 발생하는 에러입니다")
	public GlobalCodeException 잘못된_주차 = new TaskException(TaskErrorCode.INVALID_WEEK);

	@ExplainError("과거 날짜를 등록하려 할 경우 발생하는 에러입니다")
	public GlobalCodeException 과거_날짜_등록_불가 = new TaskException(TaskErrorCode.DATE_IN_THE_PAST);

	@ExplainError("day 값이 누락되었을 경우 발생하는 에러입니다")
	public GlobalCodeException day_값_누락 = new TaskException(TaskErrorCode.DAY_MISSING);

	@ExplainError("week 값이 누락되었을 경우 발생하는 에러입니다")
	public GlobalCodeException week_값_누락 = new TaskException(TaskErrorCode.WEEK_MISSING);

	@ExplainError("month 값이 누락되었을 경우 발생하는 에러입니다")
	public GlobalCodeException month_값_누락 = new TaskException(TaskErrorCode.MONTH_MISSING);

	@ExplainError("year 값이 누락되었을 경우 발생하는 에러입니다")
	public GlobalCodeException year_값_누락 = new TaskException(TaskErrorCode.YEAR_MISSING);
}
