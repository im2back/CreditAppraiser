package io.github.im2back.credit.appraiser.infra.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.im2back.credit.appraiser.model.carddtos.CardDto;
import io.github.im2back.credit.appraiser.model.carddtos.ClientCardDto;

@FeignClient(name = "ms-cards", path = "/cards")
public interface ClientResourceCard {

	@GetMapping(params = "cpf")
	ResponseEntity<List<ClientCardDto>> getClientCardByCpf(@RequestParam("cpf") String cpf);

	@GetMapping(params = "income")
	ResponseEntity<List<CardDto>> getCardsIncomeEqualToOrLess(@RequestParam("income") Long income);
}
