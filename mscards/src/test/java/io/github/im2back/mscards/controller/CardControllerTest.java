package io.github.im2back.mscards.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import io.github.im2back.mscards.model.card.Card;
import io.github.im2back.mscards.model.card.CardFlag;
import io.github.im2back.mscards.model.card.CardRequestDto;
import io.github.im2back.mscards.model.card.CardResponseDto;
import io.github.im2back.mscards.model.clientcard.ClientCardResponseDto;
import io.github.im2back.mscards.service.CardService;
import io.github.im2back.mscards.service.ClientCardService;

@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@SpringBootTest
class CardControllerTest {

	@MockBean
	private CardService cardService;
	
	@MockBean
	private ClientCardService clientCardService;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private JacksonTester<CardRequestDto> cardRequestDtoJson;
	
	@Autowired
	private JacksonTester<CardResponseDto> cardResponseDto;
	
	@Autowired
	private JacksonTester<List<Card>> listCardResponseDto;
	
	@Autowired
	private JacksonTester<List<ClientCardResponseDto>> listClientCardResponseDto;
	
	@Test
	@DisplayName("Deveria retornar : Status 201 created e o Json contendo o objeto retornadp pela classe service.")
	void register() throws IOException, Exception {
		
		//ARRANGE
		CardRequestDto jsonRequest = new CardRequestDto("Master", CardFlag.MASTERCARD, new BigDecimal(1000),new BigDecimal(1100));
		
		CardResponseDto jsonEsperado = new CardResponseDto(1l, "Master", CardFlag.MASTERCARD,new BigDecimal(1000), new BigDecimal(1100));
		
		BDDMockito.when(cardService.save(jsonRequest)).thenReturn(jsonEsperado);
		
		//ACT + ASSERT 
		var responseEndPoint = mvc
				.perform(post("/cards")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.cardRequestDtoJson.write(jsonRequest).getJson()))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", Matchers.endsWith("/cards/" + jsonEsperado.id())))
				.andReturn().getResponse();
					
		//ASSERT
		assertThat(responseEndPoint.getContentAsString()).isEqualTo(this.cardResponseDto.write(jsonEsperado).getJson());
		
	}
	
		@Test
		@DisplayName("Deveria retornar : Status 200 Ok e o Json contendo o objeto retornado pela classe service.")
		void getCardsIncomeEqualToOrLess() throws Exception {
			
			//ARRANGE
			String income = "1000";		
			Card card = new Card(1l, "Master", CardFlag.MASTERCARD, new BigDecimal(1000), new BigDecimal(1100));
			
			List<Card> listCards = new ArrayList<>();
			listCards.add(card);
			BDDMockito.when(cardService.getCardsIncomeLessOrEqual(Long.valueOf(income))).thenReturn(listCards);
				
			//ACT + ASSERT
			var responseEndPoint = mvc
			.perform(get("/cards").param("income", income))
			.andExpect(status().isOk())
			.andReturn().getResponse();
			
			//ASSERT
			assertThat(responseEndPoint.getContentAsString()).isEqualTo(this.listCardResponseDto.write(listCards).getJson());
			
		}
		
		@Test
		@DisplayName("Deveria retornar o status 200ok e o objeto correto")
		void getClientCardsByCpf() throws Exception {
			
			//ARRANJE
			String cpf = "007.692.032-13";
			List<ClientCardResponseDto> jsonEsperado = new ArrayList<>();
			
			BDDMockito.when(clientCardService.listCardByCpf(cpf)).thenReturn(jsonEsperado);
			
			//ACT
			var responseEndPoint = mvc
			.perform(get("/cards").param("cpf", cpf))
			.andExpect(status().isOk())
			.andReturn().getResponse();
			
			//ASSERT
			assertThat(responseEndPoint.getContentAsString()).isEqualTo(this.listClientCardResponseDto.write(jsonEsperado).getJson()); 
		}
	
	}
