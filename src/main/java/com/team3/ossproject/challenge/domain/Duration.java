package com.team3.ossproject.challenge.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.team3.ossproject.challenge.exception.ChallengeErrorCode;
import com.team3.ossproject.challenge.exception.ChallengeException;
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
            throw new ChallengeException(ChallengeErrorCode.INVALID_CHALLENGE_DURATION);
        }

        return duration;
    }
}