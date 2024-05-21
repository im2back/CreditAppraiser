package io.github.im2back.msclient.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.github.im2back.msclient.service.exception.ServiceClientExceptions;
import io.github.im2back.msclient.service.exception.ServiceValidationsExceptions;

@ControllerAdvice
public class GlobalHandlerException {

	@ExceptionHandler(ServiceClientExceptions.class)
	public ResponseEntity<StandardError> resourceNotFound(ServiceClientExceptions ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;

		StandardError error = new StandardError(status.value(), "Bad Request", ex.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardErrorBeanValidation> methodArgumentNotValidException(
			MethodArgumentNotValidException ex, HttpServletRequest request) {

		StandardErrorBeanValidation standardError = new StandardErrorBeanValidation();
		List<String> messages = new ArrayList<>();

		ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
			messages.add(fieldError.getField() + " : " + fieldError.getDefaultMessage());
		});

		standardError.setError("Bad Request");
		standardError.setPath(request.getRequestURI());
		standardError.setStatus(HttpStatus.BAD_REQUEST.value());
		standardError.setMessage(messages);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);

	}

	@ExceptionHandler(ServiceValidationsExceptions.class)
	public ResponseEntity<StandardError> serviceValidationsExceptions(ServiceValidationsExceptions ex,
			HttpServletRequest request) {
		var standardError = new StandardError();
		standardError.setMessage(ex.getMessage());
		standardError.setPath(request.getRequestURI());
		standardError.setStatus(HttpStatus.CONFLICT.value());
		standardError.setError("Conflict");

		return ResponseEntity.status(HttpStatus.CONFLICT).body(standardError);
	}

	@ExceptionHandler(OAuth2AuthenticationException.class)
	public ResponseEntity<String> handleOAuth2AuthenticationException(OAuth2AuthenticationException e) {
		return ResponseEntity.status(401) // Unauthorized
				.body("Erro de autenticação: " + e.getMessage());
	}

}