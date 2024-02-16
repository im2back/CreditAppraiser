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
import io.github.im2back.mscards.model.card.CardRequestDto;
import io.github.im2back.mscards.model.card.CardResponseDto;
import io.github.im2back.mscards.repository.CardRepository;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

	@Mock
	private CardRepository cardRepository;
	
	@InjectMocks
	private CardService cardService;
	
	@Captor
	private ArgumentCaptor<Card> cardCaptor;
	
	@Captor
	private ArgumentCaptor<BigDecimal> bdCaptor;
	
	@Test
	@DisplayName("Deveria salvar um novo cartão no banco de dados e retornar um Dto espelho deste objeto salvo")
	void save() {
		//ARRANJE
		CardRequestDto dto = new CardRequestDto("Name", CardFlag.MASTERCARD, new BigDecimal(1000),new BigDecimal(1100));
		
		//ACT
		CardResponseDto responseAct = cardService.save(dto);
				
		//ASSERT
		BDDMockito.then(cardRepository).should().save(cardCaptor.capture());
		var cardCaptured = cardCaptor.getValue();
		Assertions.assertEquals(cardCaptured.getCardName(), dto.cardName());
		Assertions.assertEquals(cardCaptured.getBasicLimit(), dto.basicLimit());
		Assertions.assertEquals(cardCaptured.getCardFlag(), dto.cardFlag());
		Assertions.assertEquals(cardCaptured.getIncome(), dto.income());
		
		
		Assertions.assertEquals("Name",responseAct.cardName());
		Assertions.assertEquals(CardFlag.MASTERCARD,responseAct.cardFlag());
		Assertions.assertEquals(new BigDecimal(1000), responseAct.income());
		Assertions.assertEquals(new BigDecimal(1100), responseAct.basicLimit());		
	}

	@Test
	@DisplayName("Deveria fazer uma busca do repositorio")
	void getCardsIncomeLessOrEqual() {
		//ARRANGE
		Long income = (long) 1000;
		BigDecimal incomeBD = new BigDecimal(1000);
		
		List<Card> listCard = new ArrayList<>();
				
		//ACT
		List<Card> responseAct = cardService.getCardsIncomeLessOrEqual(income);
		
		//ASSERT
		BDDMockito.then(cardRepository).should().findByIncomeLessThanEqual(bdCaptor.capture());
		var bdCaptured = bdCaptor.getValue();
		Assertions.assertEquals(incomeBD,bdCaptured);
		
		Assertions.assertEquals(listCard,responseAct);
		
		
	}
	
}
