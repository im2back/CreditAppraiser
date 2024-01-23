package io.github.im2back.msclient.controller;

import java.net.URI;

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

@RestController
@RequestMapping("clients")
public class ClientController {
	
	@Autowired
	private ClientService service;


	@PostMapping
	ResponseEntity<ClientResponseDto> saveClient(@RequestBody ClientRequestDto clientRequestDto, UriComponentsBuilder uriBuilder){
		ClientResponseDto clientResponseDto = service.saveClient(clientRequestDto);
		
		UriComponents uriComponents = uriBuilder.path("/cards/{id}").buildAndExpand(clientResponseDto.id());
	    URI location = uriComponents.toUri();
			
		return ResponseEntity.created(location).body(clientResponseDto);
		
	}
	
	@GetMapping(params="cpf")
	ResponseEntity<ClientResponseDto> getClientByCpf(@RequestParam("cpf")  String cpf){
		ClientResponseDto clientResponseDto = service.findByCpf(cpf);
		
		return ResponseEntity.ok(clientResponseDto);
	}
}
