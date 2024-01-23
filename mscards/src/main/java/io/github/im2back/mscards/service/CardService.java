package io.github.im2back.mscards.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.im2back.mscards.model.card.Card;
import io.github.im2back.mscards.model.card.CardRequestDto;
import io.github.im2back.mscards.model.card.CardResponseDto;
import io.github.im2back.mscards.repository.CardRepository;

@Service
public class CardService {
	
	@Autowired
	private CardRepository repository;
	
	public CardResponseDto save(CardRequestDto cardRequestDto) {
		Card cardSave = new Card(cardRequestDto);
		repository.save(cardSave);			
		return new CardResponseDto(cardSave);
	}

	
	public List<Card> getCardsIncomeLessOrEqual(Long income) {
		BigDecimal incomeBigDecimal = BigDecimal.valueOf(income);
		return repository.findByIncomeLessThanEqual(incomeBigDecimal);
	}
}
