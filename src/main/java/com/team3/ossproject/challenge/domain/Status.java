package com.team3.ossproject.challenge.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum Status {
    PENDING,
    MEDIUM,
    COMPLETED;


    private static final Map<String, Status> NAME_TO_ENUM_MAP = new HashMap<>();

    static {
        for (Status status : Status.values()) {
            NAME_TO_ENUM_MAP.put(status.name(), status);
        }
    }

    @JsonValue
    public String getName() {
        return name();
    }

    @JsonCreator
    public static Status fromName(String name) {
        Status status = NAME_TO_ENUM_MAP.get(name);

        if (status == null) {
            //throw new TaskException(TaskErrorCode.INVALID_STATUS_ENUM);
        }

        return status;
    }
}