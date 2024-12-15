package com.team3.ossproject.todolist.exception;

import static org.springframework.http.HttpStatus.*;

import com.team3.ossproject.global.annotation.ExplainError;
import com.team3.ossproject.global.dto.ErrorReason;
import com.team3.ossproject.global.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskErrorCode implements BaseErrorCode {

    // Validation Errors
    @ExplainError("Occurs when the title exceeds the maximum allowed length.")
    TITLE_TOO_LONG(BAD_REQUEST.value(), "TASK_400_1", "The title exceeds the maximum allowed length."),

    @ExplainError("Occurs when the description exceeds the maximum allowed length.")
    DESCRIPTION_TOO_LONG(BAD_REQUEST.value(), "TASK_400_2", "The description exceeds the maximum allowed length."),

    @ExplainError("Occurs when an invalid Task ID is provided.")
    INVALID_TASK_ID(BAD_REQUEST.value(), "TASK_400_3", "The Task ID must be a positive integer."),

    @ExplainError("Occurs when an invalid task type is provided.")
    INVALID_TYPE(BAD_REQUEST.value(), "TASK_400_4", "The task type is invalid. Allowed values are 'day', 'week', 'month'."),

    @ExplainError("Occurs when an invalid priority is provided.")
    INVALID_PRIORITY(BAD_REQUEST.value(), "TASK_400_5", "The priority is invalid. Allowed values are 'high', 'medium', 'low'."),

    @ExplainError("Occurs when the provided date is in an invalid format.")
    INVALID_DATE_FORMAT(BAD_REQUEST.value(), "TASK_400_6", "The date must follow the yyyy-MM-dd format."),

    @ExplainError("Occurs when an invalid week number is provided. Valid range is 1-52.")
    INVALID_WEEK(BAD_REQUEST.value(), "TASK_400_7", "The week number is invalid."),

    @ExplainError("Occurs when an invalid day is provided. Valid range is 1-31.")
    INVALID_DAY(BAD_REQUEST.value(), "TASK_400_8", "The day is invalid. Valid range is 1-31."),

    @ExplainError("Occurs when an invalid month is provided. Valid range is 1-12.")
    INVALID_MONTH(BAD_REQUEST.value(), "TASK_400_9", "The month is invalid. Valid range is 1-12."),

    @ExplainError("Occurs when required fields are missing.")
    MISSING_REQUIRED_FIELDS(BAD_REQUEST.value(), "TASK_400_10", "Required fields are missing."),

    @ExplainError("Occurs when a duplicate task is being created.")
    DUPLICATE_TASK(BAD_REQUEST.value(), "TASK_400_11", "A duplicate task already exists."),

    @ExplainError("Occurs when the task status update is invalid.")
    INVALID_STATUS(BAD_REQUEST.value(), "TASK_400_12", "Invalid task status update."),

    @ExplainError("Occurs when the day field is missing but required for the task type.")
    DAY_MISSING(BAD_REQUEST.value(), "TASK_400_13", "The 'day' field is missing but required for the task type."),

    @ExplainError("Occurs when the week field is missing but required for the task type.")
    WEEK_MISSING(BAD_REQUEST.value(), "TASK_400_14", "The 'week' field is missing but required for the task type."),

    @ExplainError("Occurs when the month field is missing but required for the task type.")
    MONTH_MISSING(BAD_REQUEST.value(), "TASK_400_15", "The 'month' field is missing but required for the task type."),

    @ExplainError("Occurs when the year field is missing but required for the task type.")
    YEAR_MISSING(BAD_REQUEST.value(), "TASK_400_16", "The 'year' field is missing but required for the task type."),

    // Logical Errors
    @ExplainError("Occurs when attempting to register a task for a past date.")
    DATE_IN_THE_PAST(UNPROCESSABLE_ENTITY.value(), "TASK_422_1", "Tasks cannot be registered for past dates."),

    // Authorization and Access Errors
    @ExplainError("Occurs when an invalid or missing authentication token is provided.")
    UNAUTHORIZED_ACCESS(UNAUTHORIZED.value(), "TASK_401_1", "Invalid or missing authentication token."),

    @ExplainError("Occurs when the user does not have permission to update the task.")
    FORBIDDEN_UPDATE(FORBIDDEN.value(), "TASK_403_1", "You do not have permission to update this task."),

    @ExplainError("Occurs when the user does not have permission to delete the task.")
    FORBIDDEN_DELETE(FORBIDDEN.value(), "TASK_403_2", "You do not have permission to delete this task."),

    // Resource Errors
    @ExplainError("Occurs when the requested task is not found.")
    TASK_NOT_FOUND(NOT_FOUND.value(), "TASK_404_1", "The requested task was not found.");

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
