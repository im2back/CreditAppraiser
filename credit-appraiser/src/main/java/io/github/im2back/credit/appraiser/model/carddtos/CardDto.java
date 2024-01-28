package io.github.im2back.credit.appraiser.model.carddtos;

import java.math.BigDecimal;

public record CardDto(
		Long id,
		String cardName,
		String cardFlag,
		BigDecimal basicLimit
		) {

}
