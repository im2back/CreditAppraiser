package io.github.im2back.mscards.model.card;

import java.math.BigDecimal;

public record CardRequestDto(
		String cardName,

		CardFlag cardFlag,

		BigDecimal income,

		BigDecimal basicLimit

) {

}
