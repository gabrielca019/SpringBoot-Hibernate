package com.springboot.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.services.exceptions.DatabaseException;
import com.springboot.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException error, HttpServletRequest request) {
		String mensagemErro = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError standardError = new StandardError(Instant.now(), status.value(), mensagemErro, error.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException error, HttpServletRequest request) {
		String mensagemErro = "Database integrity";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError standardError = new StandardError(Instant.now(), status.value(), mensagemErro, error.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}
	
}