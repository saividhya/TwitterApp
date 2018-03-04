package com.twitter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlingController {
	
	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFound(Exception ex) {
		ErrorResponse response = new ErrorResponse();
        response.setErrorMessage(ex.getMessage());
        response.setErrorStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(FollowException.class)
    public ResponseEntity<ErrorResponse> followError(Exception ex) {
		ErrorResponse response = new ErrorResponse();
        response.setErrorMessage(ex.getMessage());
        response.setErrorStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(UnFollowException.class)
    public ResponseEntity<ErrorResponse> unFollowError(Exception ex) {
		ErrorResponse response = new ErrorResponse();
        response.setErrorMessage(ex.getMessage());
        response.setErrorStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> badCredentials(Exception ex) {
		ErrorResponse response = new ErrorResponse();
        response.setErrorMessage(ex.getMessage());
        response.setErrorStatus(HttpStatus.FORBIDDEN);
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception ex) {
		ErrorResponse response = new ErrorResponse();
        response.setErrorMessage(ex.getMessage());
        response.setErrorStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
