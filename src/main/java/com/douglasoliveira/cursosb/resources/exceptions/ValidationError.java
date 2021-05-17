package com.douglasoliveira.cursosb.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> error = new ArrayList<>();
	
	

	public ValidationError(Long timestamp, Integer status, String error, String message, String path){
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return error;
	}

	public void addError(String fieldName, String messagem) {
		error.add(new FieldMessage(fieldName, messagem));
	}
	

	

	
}
