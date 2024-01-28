package io.github.im2back.credit.appraiser.infra.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.google.gson.Gson;

import io.github.im2back.credit.appraiser.service.exception.ServiceClientExceptions;

@ControllerAdvice
public class GlobalHandlerExceptions {

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ServiceClientExceptions.class)
	public ResponseEntity ResourceNotFound(ServiceClientExceptions e, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		String responseBody = e.getMessage().split("\\[\\{")[1].split("\\}\\]")[0];
		responseBody = "{" + responseBody + "}";
		
		Gson gson = new Gson();
		StandardError standardError = gson.fromJson(responseBody, StandardError.class);

		return ResponseEntity.status(status).body(standardError);
	}

}
