package io.github.im2back.credit.appraiser.infra.amqp;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;

import io.github.im2back.credit.appraiser.model.carddtos.IssueCard;

@AutoConfigureJsonTesters
@SpringBootTest
class IssueCardPublisherTest {
	
	@Mock
	private RabbitTemplate rabbitTemplate;
	
	@InjectMocks
	private IssueCardPublisher issueCardPublisher;
	
	@Autowired
	private JacksonTester<IssueCard> jsonIssueCard;
	
	
	@Test
	void issueCardRabbitMq() throws AmqpException, IOException {
		
		//ARRANGE
		String queue = "register.clientcard";
		IssueCard issueCard = new IssueCard(1l, "007.692.032-13", new BigDecimal(3000), "adress");
		
		//ACT
		issueCardPublisher.issueCard(issueCard);
		
		//ASSERT
		BDDMockito.then(rabbitTemplate).should().convertAndSend(queue,this.jsonIssueCard.write(issueCard).getJson());
		
		
	}

}
