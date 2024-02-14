package io.github.im2back.msclient.model.validations;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.im2back.msclient.model.Client;
import io.github.im2back.msclient.model.ClientRequestDto;
import io.github.im2back.msclient.repository.ClientRepository;
import io.github.im2back.msclient.service.exception.ServiceValidationsExceptions;

@ExtendWith(MockitoExtension.class)
class ValidCpfCannotBeDuplicatedTest {
	
	@Mock
	private ClientRepository repository;
	
	@InjectMocks
	private ValidCpfCannotBeDuplicated validCpfCannotBeDuplicated;
	
	@Test
	@DisplayName("Não deveria permitir cadastro de CPF ja existente no banco de dados")
	void validCenario01() {
		
		//ARRANGE
		ClientRequestDto dtoRequest = new ClientRequestDto("007.692.32.13", "Jefferson Souza", "31");
		
		Optional<Client> client = Optional.ofNullable(new Client((long) 1, "007.692.32-13", "segio", "31"));
		
		BDDMockito.given(repository.findByCpf(dtoRequest.cpf())).willReturn(client);
		
		//ASSERT + ACT		
		Assertions.assertThrows(ServiceValidationsExceptions.class,()-> validCpfCannotBeDuplicated.valid(dtoRequest));
	}
	
	@Test
	@DisplayName("Deveria permitir cadastro de cpf não existente no banco de dados")
	void validCenario02() {
		
		//ARRANGE
		ClientRequestDto dtoRequest = new ClientRequestDto("007.692.32.13", "Jefferson Souza", "31");		
		Optional<Client> client = Optional.ofNullable(null);

		BDDMockito.given(repository.findByCpf(dtoRequest.cpf())).willReturn(client);
		
		//ACT + ASSERT
	    Assertions.assertDoesNotThrow(() -> validCpfCannotBeDuplicated.valid(dtoRequest));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
