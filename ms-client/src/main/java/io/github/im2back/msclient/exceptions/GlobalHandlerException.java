package io.github.im2back.msclient.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.github.im2back.msclient.service.exception.ServiceClientExceptions;
import io.github.im2back.msclient.service.exception.ServiceValidationsExceptions;

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
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		  
			StandardErrorBeanValidation standardError = new StandardErrorBeanValidation();
			List<String> messages = new ArrayList<>();
	
	        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
	        	messages.add(fieldError.getField()+" : "+fieldError.getDefaultMessage());
	        });
	           
	        standardError.setError("Bad Request");
	        standardError.setPath(request.getRequestURI());
	        standardError.setStatus(HttpStatus.BAD_REQUEST.value());
	        standardError.setMessage(messages);

	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);

	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ServiceValidationsExceptions.class)
	public ResponseEntity  serviceValidationsExceptions(ServiceValidationsExceptions ex,HttpServletRequest request) {
		var standardError = new StandardError();
		standardError.setMessage(ex.getMessage());
		standardError.setPath(request.getRequestURI());
		standardError.setStatus(HttpStatus.CONFLICT.value());
		standardError.setError("Conflict");
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(standardError);
	}

	
}