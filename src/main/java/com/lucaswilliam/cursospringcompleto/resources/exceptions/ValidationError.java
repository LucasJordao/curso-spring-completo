package com.lucaswilliam.cursospringcompleto.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	//Attributes
	private List<FieldMessage> errors = new ArrayList<>();
	
	//Constructors
	public ValidationError(Integer status, String msg, Long timeStamp, String uri) {
		super(status, msg, timeStamp, uri);
	}

	//Getters and Setters
	public List<FieldMessage> getErros() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}	
	
	
}
