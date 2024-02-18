package io.github.im2back.mscards.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.im2back.mscards.model.card.Card;
import io.github.im2back.mscards.model.card.IssueCard;
import io.github.im2back.mscards.model.clientcard.ClientCard;
import io.github.im2back.mscards.repository.CardRepository;
import io.github.im2back.mscards.repository.ClientCardRepository;

@Component
public class AppraiserListener {
	
	@Autowired
	private CardRepository cardRepository;
	@Autowired
	private ClientCardRepository clientCardRepository;
	
   

	@RabbitListener(queues = "register.clientcard")
	public void receiveMessages(@Payload String msg) {
	
		try {
			
			var mapper = new ObjectMapper();
			IssueCard datas = mapper.readValue(msg, IssueCard.class);
			Card card = cardRepository.findById(datas.idCard()).orElseThrow();
			
			ClientCard clientCard = new ClientCard(null, datas.cpf(), card, datas.limitApproved());	
			
			clientCardRepository.save(clientCard);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} 
	}
	
	
}
