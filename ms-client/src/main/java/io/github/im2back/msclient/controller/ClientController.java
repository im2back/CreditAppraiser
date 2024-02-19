package io.github.im2back.msclient.controller;

import java.net.URI;

import javax.validation.Valid;

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

import io.github.im2back.msclient.model.ClientRequestDto;
import io.github.im2back.msclient.model.ClientResponseDto;
import io.github.im2back.msclient.service.ClientService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("clients")
@Slf4j
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	@GetMapping
	public String status() {
		log.info("obtendo status do microservice de clientes");
		return "ok";
	}

	@PostMapping
	ResponseEntity<ClientResponseDto> saveClient(@RequestBody @Valid ClientRequestDto clientRequestDto, UriComponentsBuilder uriBuilder){
		ClientResponseDto clientResponseDto = service.saveClient(clientRequestDto);
		
		UriComponents uriComponents = uriBuilder.path("/clients/{id}").buildAndExpand(clientResponseDto.id());
	    URI location = uriComponents.toUri();
			
		return ResponseEntity.created(location).body(clientResponseDto);		
	}
	
	@GetMapping(params="cpf")
	ResponseEntity<ClientResponseDto> getClientByCpf(@RequestParam("cpf")  String cpf){
		ClientResponseDto clientResponseDto = service.findByCpf(cpf);	
			return ResponseEntity.ok(clientResponseDto);
	}
}
