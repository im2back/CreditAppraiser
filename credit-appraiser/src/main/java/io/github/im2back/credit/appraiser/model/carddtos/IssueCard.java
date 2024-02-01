package io.github.im2back.credit.appraiser.model.carddtos;

import java.math.BigDecimal;

public record IssueCard(
		Long idCard,
		String cpf,
		BigDecimal limitApproved,
		String address
		) {

}
