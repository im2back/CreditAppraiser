package io.github.im2back.mscards.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("cards")
public class CardController {

	@Autowired
	private CardService service;

	@Autowired
	private ClientCardService clientCardservice;

	@Operation(summary = "Salva um cartão no bando de dados e retorna um DTO do cartão salvo, conforme a politica de autoridade")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Cartão registrado com sucesso", content = @Content(schema = @Schema(implementation = CardResponseDto.class))) })
	@Transactional
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ADMIN_READ','ADMIN_WRITE')")
	public ResponseEntity<CardResponseDto> register(@RequestBody CardRequestDto cardRequestDto,
			UriComponentsBuilder uriBuilder) {
		CardResponseDto cardResponseDto = service.save(cardRequestDto);

		UriComponents uriComponents = uriBuilder.path("/cards/{id}").buildAndExpand(cardResponseDto.id());
		URI location = uriComponents.toUri();

		return ResponseEntity.created(location).body(cardResponseDto);
	}

	@Operation(summary = "Retorna os cartões com renda inferior ou igual a uma renda informada")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Lista de cartões retornada com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Card.class)))) })
	@GetMapping(params = "income")
	public ResponseEntity<List<Card>> getCardsIncomeEqualToOrLess(@RequestParam("income") Long income) {
		List<Card> list = service.getCardsIncomeLessOrEqual(income);
		return ResponseEntity.ok(list);

	}

	@Operation(summary = "Retorna uma lista de cartões aprovados apartir de um CPF")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Lista de cartões retornada com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientCardResponseDto.class)))), })
	@GetMapping(params = "cpf")
	public ResponseEntity<List<ClientCardResponseDto>> getClientCardsByCpf(@RequestParam("cpf") String cpf) {
		List<ClientCardResponseDto> list = clientCardservice.listCardByCpf(cpf);
		return ResponseEntity.ok(list);

	}

}
