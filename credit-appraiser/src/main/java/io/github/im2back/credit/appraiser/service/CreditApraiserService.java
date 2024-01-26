package io.github.im2back.credit.appraiser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.im2back.credit.appraiser.infra.clients.ClientResourceClient;
import io.github.im2back.credit.appraiser.model.ClientData;
import io.github.im2back.credit.appraiser.model.ClientSituation;

@Service
public class CreditApraiserService {
	
	@Autowired
	private ClientResourceClient clientResourceClient;
		
	
	public ClientSituation getClientSituation(String cpf) {
		// obter dados do cliente - ms clientes 
			ResponseEntity<ClientData> clientDataResponse = clientResourceClient.getClientByCpf(cpf);
		//obter cartoes do cliente- ms cartoes
					
		
		return ClientSituation
				.builder()
				.clientData(clientDataResponse.getBody())
				.cards(null)
				.build();
				
	}

}
