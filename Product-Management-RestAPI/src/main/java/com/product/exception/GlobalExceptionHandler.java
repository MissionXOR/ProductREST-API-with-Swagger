package com.product.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(ProductNotFoundException.class)
	    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }

	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>(); // Use a Map

	        ex.getBindingResult().getFieldErrors().forEach(error -> {
	            String fieldName = error.getField();
	            String errorMessage = error.getDefaultMessage();
	            errors.put(fieldName, errorMessage); // Field name as key, error as value
	        });

	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
}
