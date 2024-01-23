package io.github.im2back.mscards.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.im2back.mscards.model.card.Card;

public interface CardRepository extends JpaRepository<Card,Long> {
	
	List<Card> findByIncomeLessThanEqual(BigDecimal income);
	
}
