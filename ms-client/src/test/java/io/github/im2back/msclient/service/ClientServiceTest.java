package io.github.im2back.msclient.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.im2back.msclient.model.Client;
import io.github.im2back.msclient.model.ClientRequestDto;
import io.github.im2back.msclient.model.validations.CustomerRegistrationValidation;
import io.github.im2back.msclient.repository.ClientRepository;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

	@InjectMocks
	private ClientService service;

	@Mock
	private ClientRepository repository;

	@Captor
	private ArgumentCaptor<Client> clientCaptor;

	@Spy
	private List<CustomerRegistrationValidation> validations = new ArrayList<>();

	@Mock
	private CustomerRegistrationValidation validation01;
	
	@Mock
	private CustomerRegistrationValidation validation02;

	@Test
	@DisplayName("Deveria salvar o cliente no banco de acordo com o parametro recebido")
	void saveClient() {
		// ARRANJE
		ClientRequestDto clientRequestDto = new ClientRequestDto("007.692.32-13", "Jefferson", "30");

		// ACT
		service.saveClient(clientRequestDto);

		// ASSERT
		BDDMockito.then(repository).should().save(clientCaptor.capture());
		Client clientSaved = clientCaptor.getValue();

		assertEquals(clientRequestDto.cpf(), clientSaved.getCpf());
		assertEquals(clientRequestDto.age(), clientSaved.getAge());
		assertEquals(clientRequestDto.name(), clientSaved.getName());
	}

	@Test
	@DisplayName("deveria acionar as validações")
	void saveClientValidacoes() {
		// ARRANJE
		ClientRequestDto clientRequestDto = new ClientRequestDto("007.692.32-13", "Jefferson", "30");

		validations.add(validation01);
		validations.add(validation02);

		// ACT
		service.saveClient(clientRequestDto);

		// ASSERT
		BDDMockito.then(validation01).should().valid(clientRequestDto);
		BDDMockito.then(validation02).should().valid(clientRequestDto);

	}

}
