package com.team3.ossproject.todolist.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.team3.ossproject.todolist.exception.TaskErrorCode;
import com.team3.ossproject.todolist.exception.TaskException;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum Type {
	DAY,
	WEEK,
	MONTH;


	private static final Map<String, Type> NAME_TO_ENUM_MAP = new HashMap<>();

	static {
		for (Type type : Type.values()) {
			NAME_TO_ENUM_MAP.put(type.name(), type);
		}
	}

	@JsonValue
	public String getName() {
		return name();
	}

	@JsonCreator
	public static Type fromName(String name) {
		Type type = NAME_TO_ENUM_MAP.get(name);

		if (type == null) {
			throw new TaskException(TaskErrorCode.INVALID_TYPE);
		}

		return type;
	}
}