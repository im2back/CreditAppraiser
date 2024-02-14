package io.github.im2back.msclient.model.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.im2back.msclient.model.ClientRequestDto;
import io.github.im2back.msclient.repository.ClientRepository;
import io.github.im2back.msclient.service.exception.ServiceValidationsExceptions;

@Component
public class ValidCpfCannotBeDuplicated implements CustomerRegistrationValidation {

	@Autowired
	ClientRepository repository;

	@Override
	public void valid(ClientRequestDto dto) {
		var client = repository.findByCpf(dto.cpf());
		
		if(client.isPresent()) {
			throw new ServiceValidationsExceptions("CPF já cadastrado para o usuário : "+client.get().getName());
		}
	}

}
