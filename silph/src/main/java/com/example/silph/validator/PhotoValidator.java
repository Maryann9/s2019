package com.example.silph.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.silph.model.Photo;

@Component 
public class PhotoValidator implements Validator{

	@Override
	public boolean supports(Class<?> aClass) {
		return Photo.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors error) {
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "nome", "required");		

	}
	
}