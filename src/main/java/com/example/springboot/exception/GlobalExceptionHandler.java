package com.example.springboot.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	// Handle specific exceptions
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResuorceNotFoundException(ResourceNotFoundException e, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	};
	
	// Handle api exceptions
		@ExceptionHandler(ApiExceptionHandler.class)
		public ResponseEntity<?> handleApiException(ApiExceptionHandler e, WebRequest req) {
			ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), req.getDescription(false));
			return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		};
	
	// Handle global exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	};
	
	// Handle custom validation errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleCustomValidationError(MethodArgumentNotValidException e) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Error", e.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	};
}
