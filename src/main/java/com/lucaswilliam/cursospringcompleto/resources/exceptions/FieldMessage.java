package com.lucaswilliam.cursospringcompleto.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Attributes
	private String fieldName;
	private String message;
	
	//Constructors and Overloads
	public FieldMessage() {
		
	}

	public FieldMessage(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}

	//Getters and Setters
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
