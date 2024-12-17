package com.team3.ossproject.challenge.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum Duration {
    DAY_50,
    DAY_100;


    private static final Map<String, Duration> NAME_TO_ENUM_MAP = new HashMap<>();

    static {
        for (Duration duration : Duration.values()) {
            NAME_TO_ENUM_MAP.put(duration.name(), duration);
        }
    }

    @JsonValue
    public String getName() {
        return name();
    }

    @JsonCreator
    public static Duration fromName(String name) {
        Duration duration = NAME_TO_ENUM_MAP.get(name);

        if (duration == null) {
            //throw new TaskException(TaskErrorCode.INVALID_DURATION_ENUM);
        }

        return duration;
    }
}