package io.github.im2back.mscards.amqp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.github.im2back.mscards.model.card.Card;
import io.github.im2back.mscards.model.card.CardFlag;
import io.github.im2back.mscards.model.card.IssueCard;
import io.github.im2back.mscards.model.clientcard.ClientCard;
import io.github.im2back.mscards.repository.CardRepository;
import io.github.im2back.mscards.repository.ClientCardRepository;

@SpringBootTest
@AutoConfigureJsonTesters
class AppraiserListenerTest {

	@Mock
	private CardRepository cardRepository;
	
	@Mock
	private ClientCardRepository clientCardRepository;

	@InjectMocks
	private AppraiserListener appraiserListener;

	private String issueCardJsonMsg;
	
	@Autowired
	private JacksonTester<IssueCard> issueCardJson;
	
	@Captor
	private ArgumentCaptor<ClientCard> clientCardCaptor;
	
	@Captor
	private ArgumentCaptor<Long> idCardCaptor;
	
	  @BeforeEach
	    void setUpMsg() throws IOException {
		  IssueCard issueCardDto = new IssueCard(1l, "007.692.032-13", new BigDecimal(1000),"adress");
		  this.issueCardJsonMsg = this.issueCardJson.write(issueCardDto).getJson();
		  
	    }
	
	@Test
	@DisplayName("Deveria receber uma menssagem contendo um json e acessar repositorios passando os parametros corretos")
	void receiveMessages() throws JsonMappingException, JsonProcessingException {	
		//ARRANGE
		String msg = issueCardJsonMsg; 
		IssueCard issueCard = new IssueCard(1l, "007.692.032-13", new BigDecimal(1000),"adress");
		
		Optional<Card> card = Optional.ofNullable(new Card(1l,"name card",CardFlag.MASTERCARD,new BigDecimal(1000), new BigDecimal(1200)));
		BDDMockito.when(cardRepository.findById(1l)).thenReturn(card);
		
      //ACT
        appraiserListener.receiveMessages(msg);
             
       //ASSERT
       BDDMockito.then(cardRepository).should().findById(idCardCaptor.capture());
       var idCardCaptured = idCardCaptor.getValue(); 
       Assertions.assertEquals(issueCard.idCard(), idCardCaptured);
       
       BDDMockito.then(clientCardRepository).should().save(clientCardCaptor.capture());
       var clientCardCaptured = clientCardCaptor.getValue();		
       Assertions.assertEquals(issueCard.cpf(), clientCardCaptured.getCpf()); 	
       Assertions.assertEquals(issueCard.limitApproved(), clientCardCaptured.getLimitApproved()); 
       Assertions.assertEquals(card.get(), clientCardCaptured.getCard()); 		
  
	}

}
