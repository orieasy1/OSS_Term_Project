package com.team3.ossproject.todolist.exception;

import static org.springframework.http.HttpStatus.*;

import com.team3.ossproject.global.annotation.ExplainError;
import com.team3.ossproject.global.dto.ErrorReason;
import com.team3.ossproject.global.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.team3.ossproject.global.annotation.ExplainError;

@Getter
@AllArgsConstructor
public enum TaskErrorCode implements BaseErrorCode {

    @ExplainError("Occurs when the description exceeds the maximum allowed length.")
    DESCRIPTION_TOO_LONG(BAD_REQUEST.value(), "TASK_400_2", "The description exceeds the maximum allowed length."),

    @ExplainError("Occurs when there is a duplicate task.")
    DUPLICATE_TASK(BAD_REQUEST.value(), "TASK_400_9", "A duplicate task already exists."),

    @ExplainError("Occurs when the user does not have permission to delete the task.")
    FORBIDDEN_DELETE(FORBIDDEN.value(), "TASK_403_2", "You do not have permission to delete this task."),

    @ExplainError("Occurs when the user does not have permission to update the task.")
    FORBIDDEN_UPDATE(FORBIDDEN.value(), "TASK_403_1", "You do not have permission to update this task."),

    @ExplainError("Occurs when the provided date is in an invalid format.")
    INVALID_DATE_FORMAT(BAD_REQUEST.value(), "TASK_400_6", "The date must follow the yyyy-MM-dd format."),

    @ExplainError("Occurs when an invalid priority is provided.")
    INVALID_PRIORITY(BAD_REQUEST.value(), "TASK_400_5", "The priority is invalid."),

    @ExplainError("Occurs when the task status update is invalid.")
    INVALID_STATUS_UPDATE(BAD_REQUEST.value(), "TASK_400_10", "Invalid task status update."),

    @ExplainError("Occurs when an invalid Task ID is provided.")
    INVALID_TASK_ID(BAD_REQUEST.value(), "TASK_400_3", "The Task ID must be a positive integer."),

    @ExplainError("Occurs when an invalid task type is provided.")
    INVALID_TYPE(BAD_REQUEST.value(), "TASK_400_4", "The task type is invalid."),

    @ExplainError("Occurs when an invalid week number is provided.")
    INVALID_WEEK_NUMBER(BAD_REQUEST.value(), "TASK_400_7", "The week number is invalid."),

    @ExplainError("Occurs when required fields are missing.")
    MISSING_REQUIRED_FIELDS(BAD_REQUEST.value(), "TASK_400_8", "Required fields are missing."),

    @ExplainError("Occurs when attempting to register a task for a past date.")
    PAST_DATE_NOT_ALLOWED(UNPROCESSABLE_ENTITY.value(), "TASK_422_1", "Tasks cannot be registered for past dates."),

    @ExplainError("Occurs when the requested task is not found.")
    TASK_NOT_FOUND(NOT_FOUND.value(), "TASK_404_1", "The requested task was not found."),

    @ExplainError("Occurs when the title exceeds the maximum allowed length.")
    TITLE_TOO_LONG(BAD_REQUEST.value(), "TASK_400_1", "The title exceeds the maximum allowed length."),

    @ExplainError("Occurs when an invalid or missing authentication token is provided.")
    UNAUTHORIZED_ACCESS(UNAUTHORIZED.value(), "TASK_401_1", "Invalid or missing authentication token.");

    private final Integer status; // HTTP status code
    private final String code; // Custom error code
    private final String reason; // Error message

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
