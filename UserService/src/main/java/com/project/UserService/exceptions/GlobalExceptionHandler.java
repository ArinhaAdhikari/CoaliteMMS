package com.project.UserService.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.project.UserService.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/*@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e){
		String message = e.getMessage();
		return new ResponseEntity<>(new ApiResponse(message,false),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		
		BindingResult bindingResult = ex.getBindingResult();
		
		bindingResult.getAllErrors().forEach(err->{
			String field = ((FieldError)err).getField();
			String message = err.getDefaultMessage();
			errors.put(field, message);
		});
		return new ResponseEntity<Map<String,String>>(errors,HttpStatus.NOT_ACCEPTABLE);
	}*/

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleException(ResourceNotFoundException e) {
		final var message = e.getMessage();
		return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ApiResponse> handlerHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e) {
		final var apiResponse = new ApiResponse(e.getMessage() + " for this URL", false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(
			MethodArgumentNotValidException ex) {
		final Map<String, String> errs = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach(err -> {
			final var fieldName = ((FieldError) err).getField();
			final var error = err.getDefaultMessage();
			errs.put(fieldName, error);
		});

		return new ResponseEntity<Map<String, String>>(errs, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> handleException(Exception e) {
		return new ResponseEntity<ApiResponse>(new ApiResponse("something bad happened, double check urls and"
				+ " data type compatibilities ", false), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
