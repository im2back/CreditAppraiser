package io.github.im2back.mscards.model.clientcard;

import java.math.BigDecimal;

public record ClientCardResponseDto(
		String name,
		String flag,
		BigDecimal limitAprove
		) {


	public ClientCardResponseDto(ClientCard c) {
		this(c.getCard().getCardName(),c.getCard().getCardFlag().toString(),c.getLimitAprove());
	}

}
