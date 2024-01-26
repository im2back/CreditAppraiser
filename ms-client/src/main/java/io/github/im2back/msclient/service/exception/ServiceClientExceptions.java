package io.github.im2back.msclient.service.exception;

public class ServiceClientExceptions extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;

	public ServiceClientExceptions (String msg, String cpf) {
		super("Resource not found for Cpf:" + cpf+", Exception message:" +msg); 
	}

}
