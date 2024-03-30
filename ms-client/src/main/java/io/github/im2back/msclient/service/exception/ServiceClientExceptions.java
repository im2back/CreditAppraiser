package io.github.im2back.msclient.service.exception;

public class ServiceClientExceptions extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;

	public ServiceClientExceptions (String cpf) {
		super("Resource not found for CPF : " + cpf ); 
	}

}
