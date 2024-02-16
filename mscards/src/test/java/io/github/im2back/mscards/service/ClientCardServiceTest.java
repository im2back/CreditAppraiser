package io.github.im2back.mscards.service;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import io.github.im2back.mscards.model.card.Card;
import io.github.im2back.mscards.model.card.CardFlag;
import io.github.im2back.mscards.model.clientcard.ClientCard;
import io.github.im2back.mscards.model.clientcard.ClientCardResponseDto;
import io.github.im2back.mscards.repository.ClientCardRepository;



@ExtendWith(MockitoExtension.class)
class ClientCardServiceTest {

	@InjectMocks
	private ClientCardService clientCardService;
	
	@Mock
	private ClientCardRepository clientCardRepository;
	
	@Captor
	private ArgumentCaptor<String> cpfCaptor;
	
	@Test
	@DisplayName("Deveria retornar uma lista contendo os cartões associados com determinado cpf")
	void listCardByCpf() {
		//ARRANGE
		String cpf = "007.692.032-13";
		
		List<ClientCard> listClientCard = new ArrayList<>();
		Card card1 = new Card(1l,"Teste Card",CardFlag.MASTERCARD,new BigDecimal(1000),new BigDecimal(1100));
		Card card2 = new Card(2l,"Teste Card 2",CardFlag.VISA,new BigDecimal(2000),new BigDecimal(2100));		
		listClientCard.add(new ClientCard(1l,cpf,card1,new BigDecimal(2000)));
		listClientCard.add(new ClientCard(2l,cpf,card2,new BigDecimal(3000)));
		
		BDDMockito.when(clientCardRepository.findByCpf(cpf)).thenReturn(listClientCard);
		
		//ACT	
		List<ClientCardResponseDto> responseAct = clientCardService.listCardByCpf(cpf);		
		
		//ASSERT
		BDDMockito.then(clientCardRepository).should().findByCpf(cpfCaptor.capture());
		var cpfCaptured = cpfCaptor.getValue();
		Assertions.assertEquals(cpf, cpfCaptured);		
		
		Assertions.assertEquals(cpf, responseAct.get(0).cpf());
		Assertions.assertEquals(cpf, responseAct.get(1).cpf());
		
	}

}
