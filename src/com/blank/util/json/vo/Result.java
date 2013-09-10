package com.blank.util.json.vo;

public class Result {

	private boolean success;
	private String message;

	
	
	public Result() {
		super();
	}

	public Result(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public Result(boolean success) {
		super();
		this.success = success;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

}
