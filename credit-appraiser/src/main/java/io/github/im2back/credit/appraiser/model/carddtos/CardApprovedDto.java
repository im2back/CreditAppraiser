package io.github.im2back.credit.appraiser.model.carddtos;

import java.math.BigDecimal;


public record CardApprovedDto(
		Long id,
		
		String card,
		
		String flag,
		
		BigDecimal limitApproved
		
		) {

}
