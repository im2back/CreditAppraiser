package io.github.im2back.credit.appraiser.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientSituation {

	private ClientData clientData;
	
	private List<ClientCard> cards;
}
