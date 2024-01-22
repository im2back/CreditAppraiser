package io.github.im2back.mscards.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="cards")
@EqualsAndHashCode(of = "id")
public class Card {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cardName;
	
	@Enumerated(EnumType.STRING)
	private CardFlag cardFlag;
	
	private BigDecimal income;
	
	private BigDecimal basicLimit;
	
	
	public Card(CardRequestDto cardRequestDto) {
		this.cardName = cardRequestDto.cardName();
		this.cardFlag = cardRequestDto.cardFlag();
		this.income = cardRequestDto.income();
		this.basicLimit = cardRequestDto.basicLimit();
	}

}
