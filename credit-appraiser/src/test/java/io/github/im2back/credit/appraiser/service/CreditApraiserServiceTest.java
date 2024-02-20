package io.github.im2back.credit.appraiser.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.im2back.credit.appraiser.infra.amqp.IssueCardPublisher;
import io.github.im2back.credit.appraiser.infra.clients.ClientResourceCard;
import io.github.im2back.credit.appraiser.infra.clients.ClientResourceClient;
import io.github.im2back.credit.appraiser.model.carddtos.CardDto;
import io.github.im2back.credit.appraiser.model.carddtos.ClientCardDto;
import io.github.im2back.credit.appraiser.model.carddtos.IssueCard;
import io.github.im2back.credit.appraiser.model.clientdtos.ClientDto;
import io.github.im2back.credit.appraiser.service.exception.ServiceClientExceptions;

@ExtendWith(MockitoExtension.class)
class CreditApraiserServiceTest {

	@Captor
	private ArgumentCaptor<String> cpfGetClientByCpf;

	@Captor
	private ArgumentCaptor<String> cpfGetClientCardByCpf;

	@Mock
	private ClientResourceClient clientResourceClient;

	@Mock
	private ClientResourceCard clientResourceCard;

	@Mock
	private IssueCardPublisher issueCardPublisher;

	@InjectMocks
	private CreditApraiserService service;

	@Test
	@DisplayName("Deveria receber um Cpf como parametro, acionar métodos enviando esse cpf como parametro e retornar um ClientSituation")
	void getClientSituationCenario01() {
		// ARRANGE
		String cpf = "007.692.032-13";

		ClientDto clientDto = new ClientDto(1L, "Jefferson", 30);
		ResponseEntity<ClientDto> clientDtoResponse = ResponseEntity.ok(clientDto);
		BDDMockito.when(clientResourceClient.getClientByCpf(cpf)).thenReturn(clientDtoResponse);

		ClientCardDto card1 = new ClientCardDto("Card 1", "Visa", new BigDecimal("10000"));
		ClientCardDto card2 = new ClientCardDto("Card 2", "Mastercard", new BigDecimal("15000"));
		List<ClientCardDto> cards = Arrays.asList(card1, card2);

		ResponseEntity<List<ClientCardDto>> clientCardResponse = ResponseEntity.ok(cards);
		BDDMockito.when(clientResourceCard.getClientCardByCpf(cpf)).thenReturn(clientCardResponse);

		// ACT
		var response = service.getClientSituation(cpf);

		// ASSERT
		BDDMockito.then(clientResourceCard).should().getClientCardByCpf(cpfGetClientCardByCpf.capture());
		var cpfGetClientCardByCpfCaptured = cpfGetClientCardByCpf.getValue();
		Assertions.assertEquals(cpf, cpfGetClientCardByCpfCaptured);

		BDDMockito.then(clientResourceClient).should().getClientByCpf(cpfGetClientByCpf.capture());
		var cpfGetClientByCpfCaptured = cpfGetClientByCpf.getValue();
		Assertions.assertEquals(cpf, cpfGetClientByCpfCaptured);

		Assertions.assertEquals(clientDto, response.getClientDto());
		Assertions.assertEquals(clientCardResponse.getBody(), response.getCards());
	}
	
	@Test
	@DisplayName("Deveria lançar uma exceção para um método que retorna exceção")
	void getClientSituationCenario02() {
		// ARRANGE
		String cpf = "007.692.032-13";

		BDDMockito.when(clientResourceClient.getClientByCpf(cpf)).thenThrow(new ServiceClientExceptions("Exceção teste"));

		// ACT + assert
		Assertions.assertThrows(ServiceClientExceptions.class, () -> {
			service.getClientSituation(cpf);
			});

		BDDMockito.then(clientResourceClient).should().getClientByCpf(cpf);
	}
	
	@Test
	@DisplayName("Deveria retornar uma lista do objeto cartão aprovado")
	void evaluatingClients() {
		//ARRANGE
		String cpf = "007.692.032-13";
		Long income = (long) 1000;
		
		ClientDto clientDto = new ClientDto(1L, "Jefferson", 30);
		ResponseEntity<ClientDto> clientDtoResponse = ResponseEntity.ok(clientDto);
		BDDMockito.when(clientResourceClient.getClientByCpf(cpf)).thenReturn(clientDtoResponse);
		
		List<CardDto> listCardDto = new ArrayList<>();
		CardDto element1 = new CardDto(1l, "card 1", "MASTERCARD", new BigDecimal(1000));
		CardDto element2 = new CardDto(2l, "card 2", "VISA", new BigDecimal(1000));
		listCardDto.addAll(Arrays.asList(element1,element2));
		ResponseEntity<List<CardDto>> listCardDtoResponse = ResponseEntity.ok(listCardDto);
		BDDMockito.when(clientResourceCard.getCardsIncomeEqualToOrLess(income)).thenReturn(listCardDtoResponse);
		
		//ACT
		var response = service.evaluatingClients(cpf, income);
		
		//ASSERT
		BDDMockito.then(clientResourceClient).should().getClientByCpf(cpf);
		BDDMockito.then(clientResourceCard).should().getCardsIncomeEqualToOrLess(income);
		
		Assertions.assertEquals(2, response.cards().size());
		Assertions.assertEquals(response.cards().get(0).flag(), "MASTERCARD");
		Assertions.assertEquals(response.cards().get(0).card(), "card 1");
		Assertions.assertEquals(response.cards().get(1).flag(), "VISA");
		Assertions.assertEquals(response.cards().get(1).card(), "card 2");
	
	}

	@Test
	@DisplayName("teste")
	void requestCardIssuance() throws JsonProcessingException {
		//ARRRANGE
		IssueCard datas = new IssueCard(1l, "007.692.032-13", new BigDecimal(3000), "adress");
			
		//ACT
		var response = service.requestCardIssuance(datas);
		BDDMockito.then(issueCardPublisher).should().issueCard(datas);
		
		//ASSERT
		Assertions.assertNotNull(response.protocol(), "O protocolo não deveria ser nulo.");

	}
	
}
