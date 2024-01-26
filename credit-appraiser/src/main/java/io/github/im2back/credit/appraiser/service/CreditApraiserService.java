package io.github.im2back.credit.appraiser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException;
import io.github.im2back.credit.appraiser.infra.clients.ClientResourceClient;
import io.github.im2back.credit.appraiser.infra.clients.ClientResourceClientCard;
import io.github.im2back.credit.appraiser.model.ClientCard;
import io.github.im2back.credit.appraiser.model.ClientData;
import io.github.im2back.credit.appraiser.model.ClientSituation;
import io.github.im2back.credit.appraiser.service.exception.ServiceClientExceptions;

@Service
public class CreditApraiserService {

	@Autowired
	private ClientResourceClient clientResourceClient;

	@Autowired
	private ClientResourceClientCard clientResourceClientCard;

	public ClientSituation getClientSituation(String cpf) {
		try {
			ResponseEntity<ClientData> clientDataResponse = clientResourceClient.getClientByCpf(cpf);
			ResponseEntity<List<ClientCard>> clientCardResponse = clientResourceClientCard.getClientByCpf(cpf);

			return ClientSituation.builder().clientData(clientDataResponse.getBody())
					.cards(clientCardResponse.getBody()).build();

		} catch (FeignException.NotFound e) {
			throw new ServiceClientExceptions(e.getMessage());
		}

	}

}
