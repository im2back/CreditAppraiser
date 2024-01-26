package io.github.im2back.credit.appraiser.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientCard {
	
	private String cardName;
	
	private String cardFlag;
	
	private BigDecimal limitApproved;

}
