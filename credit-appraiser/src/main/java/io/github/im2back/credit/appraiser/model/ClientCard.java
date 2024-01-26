package io.github.im2back.credit.appraiser.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientCard {
	
	private String name;
	
	private String flag;
	
	private BigDecimal limitApproved;

}
