package io.github.im2back.credit.appraiser.model;

import java.util.List;

import io.github.im2back.credit.appraiser.model.carddtos.ClientCardDto;
import io.github.im2back.credit.appraiser.model.clientdtos.ClientDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@Builder
public class ClientSituation {

	private ClientDto clientDto;
	
	private List<ClientCardDto> cards;

	public ClientSituation(ClientDto clientDto, List<ClientCardDto> cards) {
		
		this.clientDto = clientDto;
		this.cards = cards;
	}
	
	
	
}
