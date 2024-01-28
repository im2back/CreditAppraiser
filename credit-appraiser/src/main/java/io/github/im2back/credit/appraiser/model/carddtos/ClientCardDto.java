package io.github.im2back.credit.appraiser.model.carddtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientCardDto {
	
	private String cardName;
	
	private String cardFlag;
	
	private BigDecimal limitApproved;

}
