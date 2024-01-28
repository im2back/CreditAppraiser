package io.github.im2back.credit.appraiser.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException;
import io.github.im2back.credit.appraiser.infra.clients.ClientResourceCard;
import io.github.im2back.credit.appraiser.infra.clients.ClientResourceClient;
import io.github.im2back.credit.appraiser.model.ClientSituation;
import io.github.im2back.credit.appraiser.model.assessmentdto.ResultAssessmentClientResponseDto;
import io.github.im2back.credit.appraiser.model.carddtos.CardApprovedDto;
import io.github.im2back.credit.appraiser.model.carddtos.CardDto;
import io.github.im2back.credit.appraiser.model.carddtos.ClientCardDto;
import io.github.im2back.credit.appraiser.model.clientdtos.ClientDto;
import io.github.im2back.credit.appraiser.service.exception.ServiceClientExceptions;

@Service
public class CreditApraiserService {

	@Autowired
	private ClientResourceClient clientResourceClient;
	@Autowired
	private ClientResourceCard clientResourceCard;

	public ClientSituation getClientSituation(String cpf) {
		ClientDto clientResponse = getClientByCpf(cpf);
			ResponseEntity<List<ClientCardDto>> clientCardResponse = clientResourceCard.getClientCardByCpf(cpf);
				List<ClientCardDto> listResponse = clientCardResponse.getBody();
					return new ClientSituation(clientResponse, listResponse);
	}

	public ResultAssessmentClientResponseDto evaluatingClients(String cpf, Long income) {
		Integer age = getClientByCpf(cpf).getAge();
			List<CardDto> listOfAvailableCards = getCardsIncomeEqualToOrLess(income);	
				List<CardApprovedDto> listCardsApproveds = createListCardsApproveds(listOfAvailableCards, age);	
					return new ResultAssessmentClientResponseDto(listCardsApproveds);
	}

	private List<CardDto> getCardsIncomeEqualToOrLess(Long income) {
		ResponseEntity<List<CardDto>> clientCardResponse = clientResourceCard.getCardsIncomeEqualToOrLess(income);	
		List<CardDto> listOfAvailableCards = clientCardResponse.getBody();
		return listOfAvailableCards;
	}

	private ClientDto getClientByCpf(String cpf) {
		try {
			ResponseEntity<ClientDto> clientDataResponse = clientResourceClient.getClientByCpf(cpf);
			ClientDto client = clientDataResponse.getBody();
			return client;
		} catch (FeignException.NotFound e) {
			throw new ServiceClientExceptions(e.getMessage());
		}
	}

	private BigDecimal creditRatingAlgorithm(BigDecimal limitBasic, Integer age) {
		BigDecimal ageBD = BigDecimal.valueOf(age);
		BigDecimal factor = ageBD.divide(BigDecimal.valueOf(10));
		BigDecimal limitapproved = factor.multiply(limitBasic);
		return limitapproved;
	}
	
	private List<CardApprovedDto> createListCardsApproveds(List<CardDto> listOfAvailableCards,Integer age) {
		List<CardApprovedDto> listCardsApproveds = new ArrayList<>();
		for (CardDto card : listOfAvailableCards) {
			BigDecimal limitApproved = creditRatingAlgorithm(card.basicLimit(), age);
			CardApprovedDto approved = new CardApprovedDto(card.cardName(), card.cardFlag(), limitApproved);
			listCardsApproveds.add(approved);
		}
		return listCardsApproveds;
	}
	

}
