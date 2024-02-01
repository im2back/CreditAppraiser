package io.github.im2back.credit.appraiser.infra.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.im2back.credit.appraiser.model.carddtos.IssueCard;

@Component
public class IssueCardPublisher {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	

	public void issueCard(IssueCard datas) throws JsonProcessingException {
		var json = convertIntoJson(datas);
		rabbitTemplate.convertAndSend("register.clientcard",json);
	}


	private Object convertIntoJson(IssueCard datas) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		var json = mapper.writeValueAsString(datas);
		
		return json;	
	}
	
}
