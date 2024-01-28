package io.github.im2back.credit.appraiser.model.carddtos;

import java.math.BigDecimal;


public record CardApprovedDto(
		String card,
		
		String flag,
		
		BigDecimal limitApproved
		
		) {

}
