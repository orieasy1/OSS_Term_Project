package com.team3.ossproject.common.valid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<ValidDate, LocalDate> {

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		if (value == null) {
			return true; // null 값은 다른 어노테이션에서 처리하도록 함
		}

		LocalDate today = LocalDate.now();
		LocalDate oneMonthLater = today.plusMonths(1);

		// 과거 날짜인 경우
		if (value.isBefore(today)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("PAST_DATE").addConstraintViolation();
			return false;
		}


		return true;
	}
}
