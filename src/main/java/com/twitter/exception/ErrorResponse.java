package com.twitter.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	
	private String errorMessage;
	private HttpStatus errorStatus;
	
	public HttpStatus getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(HttpStatus errorStatus) {
		this.errorStatus = errorStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
