package io.github.im2back.mscards.model.clientcard;

import java.math.BigDecimal;

public record ClientCardResponseDto(
		String cardName,
		String cardFlag,
		BigDecimal limitApproved
		) {


	public ClientCardResponseDto(ClientCard c) {
		this(c.getCard().getCardName(),c.getCard().getCardFlag().toString(),c.getLimitApproved());
	}

}
