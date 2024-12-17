package com.team3.ossproject.challenge.exception;


import com.team3.ossproject.global.exception.GlobalCodeException;
import com.team3.ossproject.todolist.exception.TaskErrorCode;
import lombok.Getter;

@Getter
public class ChallengeException extends GlobalCodeException {
    public ChallengeException(ChallengeErrorCode errorCode) {
        super(errorCode);
    }
}
