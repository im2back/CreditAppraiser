package io.github.im2back.mscards.model.card;

import java.math.BigDecimal;

public record CardResponseDto(
		Long id,
		
		String cardName,

		CardFlag cardFlag,

		BigDecimal income,

		BigDecimal basicLimit
		) {

	public CardResponseDto(Card card) {
		this(card.getId(),card.getCardName(),card.getCardFlag(),card.getIncome(),card.getBasicLimit());
	}

}
