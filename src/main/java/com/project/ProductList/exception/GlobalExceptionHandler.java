package com.project.ProductList.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.ProductList.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
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
	}
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<ApiResponse> handleResourceAlreadyExistException(ResourceAlreadyExistException e){
		String message = e.getMessage();
		return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
}


