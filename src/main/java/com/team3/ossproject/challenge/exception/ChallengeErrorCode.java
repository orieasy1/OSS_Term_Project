package com.team3.ossproject.challenge.exception;

import com.team3.ossproject.global.annotation.ExplainError;
import com.team3.ossproject.global.dto.ErrorReason;
import com.team3.ossproject.global.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.team3.ossproject.global.consts.TimetodoStatic.*;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Getter
@AllArgsConstructor
public enum ChallengeErrorCode implements BaseErrorCode {

    @ExplainError("챌린지가 존재하지 않을 때 발생하는 오류입니다.")
    CHALLENGE_NOT_FOUND(NOT_FOUND, "CHALLENGE_404_1", "해당 챌린지가 존재하지 않습니다."),

    @ExplainError("챌린지의 시작 날짜가 현재 날짜보다 이후일 때 발생하는 오류입니다.")
    CHALLENGE_START_DATE_INVALID(BAD_REQUEST, "CHALLENGE_400_1", "챌린지 시작 날짜가 유효하지 않습니다."),

    @ExplainError("챌린지 상태가 잘못된 형식일 때 발생하는 오류입니다.")
    INVALID_CHALLENGE_STATUS(BAD_REQUEST, "CHALLENGE_400_2", "챌린지 상태가 잘못된 형식입니다."),

    @ExplainError("진행률이 0에서 100 사이가 아닌 경우 발생하는 오류입니다.")
    PROGRESS_RATE_OUT_OF_RANGE(BAD_REQUEST, "CHALLENGE_400_3", "진행률은 0과 100 사이여야 합니다."),

    @ExplainError("챌린지 남은 일 수가 음수인 경우 발생하는 오류입니다.")
    NEGATIVE_REMAINING_DAYS(BAD_REQUEST, "CHALLENGE_400_4", "남은 일 수가 음수일 수 없습니다."),

    @ExplainError("챌린지의 Duration 값이 잘못된 형식일 때 발생하는 오류입니다.")
    INVALID_CHALLENGE_DURATION(BAD_REQUEST, "CHALLENGE_400_5", "챌린지의 Duration 값이 유효하지 않습니다."),

    @ExplainError("Occurs when attempting to register a task for a past date.")
    PAST_DATE_NOT_ALLOWED(UNPROCESSABLE_ENTITY.value(), "TASK_422_1", "Tasks cannot be registered for past dates.");


    private final Integer status;
    private final String code;
    private final String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder()
                .reason(reason)
                .code(code)
                .status(status)
                .build();
    }

    @Override
    public String getExplainError() {
        return this.reason;
    }
}

