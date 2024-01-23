package io.github.im2back.mscards.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.im2back.mscards.model.card.Card;
import io.github.im2back.mscards.model.card.CardRequestDto;
import io.github.im2back.mscards.model.card.CardResponseDto;
import io.github.im2back.mscards.model.clientcard.ClientCardResponseDto;
import io.github.im2back.mscards.service.CardService;
import io.github.im2back.mscards.service.ClientCardService;

@RestController
@RequestMapping("cards")
public class CardController {
	
	@Autowired
	private CardService service;
	
	@Autowired
	private ClientCardService cliendCardservice;
	
	@Transactional
	@PostMapping
	public ResponseEntity<CardResponseDto> register(@RequestBody CardRequestDto cardRequestDto, UriComponentsBuilder uriBuilder){
			CardResponseDto cardResponseDto = service.save(cardRequestDto);
			
		    UriComponents uriComponents = uriBuilder.path("/cards/{id}").buildAndExpand(cardResponseDto.id());
		    URI location = uriComponents.toUri();
			
			return ResponseEntity.created(location).body(cardResponseDto);
	}
	
	
	@GetMapping(params = "income")
	public ResponseEntity<List<Card>> getCardsIncomeEqualToOrLess(@RequestParam("income") Long income ){
		List<Card> list = service.getCardsIncomeLessOrEqual(income);	
		return ResponseEntity.ok(list);
		
	}
	
	@GetMapping(params = "cpf")
	public ResponseEntity<List<ClientCardResponseDto>> getClientCardsByCpf(@RequestParam("cpf") String cpf ){
		List<ClientCardResponseDto> list = cliendCardservice.listCardByCpf(cpf);	
		return ResponseEntity.ok(list);
		
	}

	
}
