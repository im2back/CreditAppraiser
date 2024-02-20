package io.github.im2back.credit.appraiser.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
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

import io.github.im2back.credit.appraiser.model.ClientSituation;
import io.github.im2back.credit.appraiser.model.ProtocolIssueCard;
import io.github.im2back.credit.appraiser.model.assessmentdto.DataAssessmentRequestDto;
import io.github.im2back.credit.appraiser.model.assessmentdto.ResultAssessmentClientResponseDto;
import io.github.im2back.credit.appraiser.model.carddtos.CardApprovedDto;
import io.github.im2back.credit.appraiser.model.carddtos.ClientCardDto;
import io.github.im2back.credit.appraiser.model.carddtos.IssueCard;
import io.github.im2back.credit.appraiser.model.clientdtos.ClientDto;
import io.github.im2back.credit.appraiser.service.CreditApraiserService;



@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class CreditAppraiserControllerTest {

	@MockBean
	private CreditApraiserService service;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private JacksonTester<DataAssessmentRequestDto> jsonDataAssessmentRequestDto;
	
	@Autowired
	private JacksonTester<ResultAssessmentClientResponseDto> jsonResultAssessmentClientResponseDto;
	
	@Autowired
	private JacksonTester<IssueCard> jsonIssueCard;
	
	@Autowired
	private JacksonTester<ProtocolIssueCard> jsonProtocolIssueCard;
	
	@Autowired
	private JacksonTester<ClientSituation> jsonClientSituation;
	
	@Test
	@DisplayName("Deveria retornar  status 200ok, deveria passar o parametro correto e devolver e o objeto correto")
	void creditAssessment() throws IOException, Exception {
		
		 //ARRANGE
		DataAssessmentRequestDto dataAssessmentRequestDto = new DataAssessmentRequestDto("007.692.032-13", (long) 1000 );
		
		List<CardApprovedDto> list = new ArrayList<>();
		CardApprovedDto card1 = new CardApprovedDto("Card1", "FLAG1", new BigDecimal(3000));
		CardApprovedDto card2 = new CardApprovedDto("Card2", "FLAG2", new BigDecimal(3000));
		list.add(card1);
		list.add(card2);
		ResultAssessmentClientResponseDto result = new ResultAssessmentClientResponseDto(list);
		BDDMockito.when(service.evaluatingClients(dataAssessmentRequestDto.cpf(), dataAssessmentRequestDto.income())).thenReturn(result);
		
		
		 // ACT + ASSERT
		var responseRequest = mvc
				.perform(post("/credit-appraiser")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonDataAssessmentRequestDto.write(dataAssessmentRequestDto).getJson()))
				.andExpect(status().isOk())
				.andReturn().getResponse();
					
		//ASSERT
		BDDMockito.then(service).should().evaluatingClients(dataAssessmentRequestDto.cpf(), dataAssessmentRequestDto.income());
		Assertions.assertEquals(this.jsonResultAssessmentClientResponseDto.write(result).getJson(), responseRequest.getContentAsString());	
			
	}
	
	@Test
	@DisplayName("Deveria retornar : Status 200 ok, Json contendo o Protocolo gerado na classe service e chamar os parametros corretos.")
	void issueCard() throws IOException, Exception {
		//ARRANGE
		IssueCard issue = new IssueCard(1l, "007.692.32-13", new BigDecimal(3000), "adress");
		ProtocolIssueCard protocol = new ProtocolIssueCard("PROT123456789");	
		BDDMockito.when(service.requestCardIssuance(issue)).thenReturn(protocol);
		
		//ACT + ASSERT	
	var response = mvc
		.perform(post("/credit-appraiser/request-card")
		.contentType(MediaType.APPLICATION_JSON)
		.content(this.jsonIssueCard.write(issue).getJson()))
		.andExpectAll(status().isOk())
		.andReturn().getResponse();
		
		//ASSERT
	BDDMockito.then(service).should().requestCardIssuance(issue);
	Assertions.assertEquals(this.jsonProtocolIssueCard.write(protocol).getJson(), response.getContentAsString());

	}
	
	@Test
	@DisplayName("Deveria retornar : Status 200ok e um client situation e utilizar os parametros corretos.")
	void checkStatusClient() throws Exception {
		//ARRANGE
		String cpf = "007.692.032-13";
		
		ClientDto clientDto = new ClientDto(1l, "Jefferson", 30);
	
		List<ClientCardDto> listClientCardDto = new ArrayList<>();
		ClientCardDto clientCardDto1 = new ClientCardDto("Card Name", "Card Flag", new BigDecimal(3000));
		ClientCardDto clientCardDto2 = new ClientCardDto("Card Name2", "Card Flag2", new BigDecimal(6000));
		listClientCardDto.add(clientCardDto2);
		listClientCardDto.add(clientCardDto1);
		
		ClientSituation clientSituation = new ClientSituation(clientDto, listClientCardDto);
		BDDMockito.when(service.getClientSituation(cpf)).thenReturn(clientSituation);
		
		//ACT
		var response = mvc.perform(get("/credit-appraiser/client-status").param("cpf", cpf))
		.andExpect(status().isOk())
		.andReturn().getResponse();
		
		//ASSERT
		BDDMockito.then(service).should().getClientSituation(cpf);
		Assertions.assertEquals(this.jsonClientSituation.write(clientSituation).getJson(), response.getContentAsString());
	
	}

}
