package com.mitrian.validation;

import com.mitrian.exception.ValidationException;

public interface Validator<T>{
	void validate(T obj) throws ValidationException;

}
