package io.github.im2back.credit.appraiser.infra.util;

import java.math.BigDecimal;

public class Utils {

	
	public static BigDecimal creditRatingAlgorithm(BigDecimal limitBasic, Integer age) {
		BigDecimal ageBD = BigDecimal.valueOf(age);
		BigDecimal factor = ageBD.divide(BigDecimal.valueOf(10));
		BigDecimal limitapproved = factor.multiply(limitBasic);
		return limitapproved;
	}
	
}
