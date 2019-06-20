package com.example.silph.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.silph.model.Executive;

@Component
public class ExecutiveValidator implements Validator{

	@Override
	public void validate(Object o, Errors error) {
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "userName", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "password", "required");
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Executive.class.equals(clazz);
	}

}