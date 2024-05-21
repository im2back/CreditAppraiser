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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("clients")
public class ClientController {

	@Autowired
	private ClientService service;

	@Operation(summary = "Salva um Client no banco de dados e retorna um DTO do Client salvo")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Retorna um DTO de Client em caso de sucesso"),
			@ApiResponse(responseCode = "400", description = "Retorna uma exceção tratada, lançada apartir do Bean Validation"
					+ "uma exceção tratata"),
			@ApiResponse(responseCode = "409", description = "Retorna uma exceção lançada pela classe ServiceValidationsExceptions")})
	@PostMapping
	ResponseEntity<ClientResponseDto> saveClient(@RequestBody @Valid ClientRequestDto clientRequestDto,
			UriComponentsBuilder uriBuilder) {
		ClientResponseDto clientResponseDto = service.saveClient(clientRequestDto);

		UriComponents uriComponents = uriBuilder.path("/clients/{id}").buildAndExpand(clientResponseDto.id());
		URI location = uriComponents.toUri();

		return ResponseEntity.created(location).body(clientResponseDto);
	}

	@Operation
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Retorna um DTO de Client aparit de um CPF"),
			@ApiResponse(responseCode = "400", description = "Retorna uma exceção personalizada do tipo ServiceClientExceptions"
					+ "caso o CPF não exista") })
	@GetMapping(params = "cpf")
	ResponseEntity<ClientResponseDto> getClientByCpf(@RequestParam("cpf") String cpf) {
		ClientResponseDto clientResponseDto = service.findByCpf(cpf);
		return ResponseEntity.ok(clientResponseDto);
	}
}
