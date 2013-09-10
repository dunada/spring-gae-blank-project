package com.blank.util.exception;

import java.util.Map;


public class ValidationException extends Exception {
	
	private static final long serialVersionUID = 1693326520514801244L;
	private Map<String,String> errors=null;

	public ValidationException(Map<String,String> errors) {
		super();
		this.errors=errors;
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	public Map<String, String> getErrors() {
		return errors;
	}
	
	

	
	

}
