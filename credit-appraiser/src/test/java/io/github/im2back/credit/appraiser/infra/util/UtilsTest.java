package io.github.im2back.credit.appraiser.infra.util;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilsTest {

	@Test
	void test() {
		//ARRANGE
		BigDecimal limitBasic = new BigDecimal(1000);
		Integer age = 30;
		//ACT
		
		var response = Utils.creditRatingAlgorithm(limitBasic, age);
				
		//ASSERT
		 Assertions.assertEquals(0, response.compareTo(new BigDecimal(3000)), "The credit limit calculation is incorrect");
		
		
	}

}
