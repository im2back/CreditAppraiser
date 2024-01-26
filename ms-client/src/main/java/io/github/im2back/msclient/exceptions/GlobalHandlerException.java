package io.github.im2back.msclient.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.github.im2back.msclient.service.exception.ServiceClientExceptions;

@ControllerAdvice
public class GlobalHandlerException {

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ServiceClientExceptions.class)
	public ResponseEntity ResourceNotFound(ServiceClientExceptions e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		String[] parts = e.getMessage().split("\\,"); 
		
		StandardError error = new StandardError(status.value(), parts[1], parts[0], request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);
}

	
}