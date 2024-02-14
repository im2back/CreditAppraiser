package io.github.im2back.msclient.model.validations;

import io.github.im2back.msclient.model.ClientRequestDto;

public interface CustomerRegistrationValidation {
	
	void valid(ClientRequestDto dto);
}
