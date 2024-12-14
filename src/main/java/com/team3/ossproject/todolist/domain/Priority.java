package com.team3.ossproject.todolist.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.team3.ossproject.todolist.exception.TaskErrorCode;
import com.team3.ossproject.todolist.exception.TaskException;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum Priority {
    PENDING,
    COMPLETED,
    FAILED;


    private static final Map<String, Priority> NAME_TO_ENUM_MAP = new HashMap<>();

    static {
        for (Priority priority : Priority.values()) {
            NAME_TO_ENUM_MAP.put(priority.name(), priority);
        }
    }

    @JsonValue
    public String getName() {
        return name();
    }

    @JsonCreator
    public static Priority fromName(String name) {
        Priority priority = NAME_TO_ENUM_MAP.get(name);

        if (priority == null) {
            throw new TaskException(TaskErrorCode.INVALID_PRIORITY);
        }

        return priority;
    }
}