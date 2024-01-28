package io.github.im2back.credit.appraiser.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.im2back.credit.appraiser.model.clientdtos.ClientDto;


@FeignClient(name = "ms-clients", path = "/clients")
public interface  ClientResourceClient {
	
	@GetMapping(params="cpf")
	ResponseEntity<ClientDto> getClientByCpf(@RequestParam("cpf")  String cpf);
		

}
