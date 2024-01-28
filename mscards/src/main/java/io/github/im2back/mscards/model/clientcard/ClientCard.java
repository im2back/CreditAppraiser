package io.github.im2back.mscards.model.clientcard;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.github.im2back.mscards.model.card.Card;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_clientcard")
@EqualsAndHashCode(of = "id")
public class ClientCard {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	private String cpf;
	
	@ManyToOne
	@JoinColumn(name = "id_card")
	private Card card;
	
	@Column(name = "limit_aprove")
	private BigDecimal limitApproved;

}
