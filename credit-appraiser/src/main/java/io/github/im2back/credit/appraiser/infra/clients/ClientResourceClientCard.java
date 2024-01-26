package io.github.im2back.credit.appraiser.infra.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.im2back.credit.appraiser.model.ClientCard;


@FeignClient(name = "ms-cards", path = "/cards")
public interface  ClientResourceClientCard {
	
	@GetMapping(params="cpf")
	ResponseEntity<List<ClientCard>> getClientByCpf(@RequestParam("cpf")  String cpf);
		

}
