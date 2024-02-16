package io.github.im2back.msclient.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import io.github.im2back.msclient.model.ClientResponseDto;
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
	
	@Captor
	private ArgumentCaptor<String> cpfCaptor;

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
		var returned = service.saveClient(clientRequestDto);

		// ASSERT
		BDDMockito.then(repository).should().save(clientCaptor.capture());
		Client clientSaved = clientCaptor.getValue();

		assertEquals(clientRequestDto.cpf(), clientSaved.getCpf());
		assertEquals(clientRequestDto.age(), clientSaved.getAge());
		assertEquals(clientRequestDto.name(), clientSaved.getName());
		
		assertEquals(returned.cpf(), clientRequestDto.cpf());
		assertEquals(returned.name(), clientRequestDto.name());
		assertEquals(returned.age(), clientRequestDto.age());
		
	}

	@Test
	@DisplayName("O método service.saveClient, deveria acionar as validações")
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
	
	@Test
	@DisplayName("Deveria retornar o cliente com o cpf informado")
	void findByCpfTest() {
		//ARRANGE
		String cpf = "007.692.032-13"; 
		

		var client = Optional.ofNullable(new Client(1l, "007.692.032-13", "Jefferson", "30"));
		
		BDDMockito.when(repository.findByCpf(cpf)).thenReturn(client);
		
		//ACT
		ClientResponseDto response = service.findByCpf(cpf);
		
		//ASSERT
		BDDMockito.then(repository).should().findByCpf(cpfCaptor.capture());
		var cpfRepository = cpfCaptor.getValue();	
		assertEquals(cpf, cpfRepository);
				
		assertEquals(response.cpf(), client.get().getCpf());
		assertEquals("Jefferson", response.name());
		
		
		
	}

}
