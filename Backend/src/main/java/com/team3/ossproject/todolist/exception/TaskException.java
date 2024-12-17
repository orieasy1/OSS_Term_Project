package com.team3.ossproject.todolist.exception;


import com.team3.ossproject.global.exception.GlobalCodeException;
import lombok.Getter;

@Getter
public class TaskException extends GlobalCodeException {
    public TaskException(TaskErrorCode errorCode) {
        super(errorCode);
    }
}
