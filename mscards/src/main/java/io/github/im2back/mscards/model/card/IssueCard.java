package io.github.im2back.mscards.model.card;

import java.math.BigDecimal;

public record IssueCard(
		Long idCard,
		String cpf,
		BigDecimal limitApproved,
		String address
		) {

}
