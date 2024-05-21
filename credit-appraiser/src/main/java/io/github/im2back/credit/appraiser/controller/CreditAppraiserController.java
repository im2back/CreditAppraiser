package io.github.im2back.credit.appraiser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.im2back.credit.appraiser.model.ClientSituation;
import io.github.im2back.credit.appraiser.model.ProtocolIssueCard;
import io.github.im2back.credit.appraiser.model.assessmentdto.DataAssessmentRequestDto;
import io.github.im2back.credit.appraiser.model.assessmentdto.ResultAssessmentClientResponseDto;
import io.github.im2back.credit.appraiser.model.carddtos.IssueCard;
import io.github.im2back.credit.appraiser.service.CreditApraiserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("credit-appraiser")
public class CreditAppraiserController {

	@Autowired
	private CreditApraiserService service;

	@Operation(summary = "Retorna um dto contendo a situação cadastral do cliente")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Situação cadastral retornada com sucesso", content = @Content(schema = @Schema(implementation = ClientSituation.class))) })
	@GetMapping(value = "client-status", params = "cpf")
	public ResponseEntity<ClientSituation> checkStatusClient(@RequestParam("cpf") String cpf) {

		ClientSituation customerSituation = service.getClientSituation(cpf);
		return ResponseEntity.ok(customerSituation);
	}

	@Operation(summary = "Retorna um dto contendo o resultado da avaliação de crédito")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Valiação de crédito retornada com sucesso", content = @Content(schema = @Schema(implementation = ResultAssessmentClientResponseDto.class))) })
	@PostMapping
	public ResponseEntity<ResultAssessmentClientResponseDto> creditAssessment(
			@RequestBody DataAssessmentRequestDto data) {

		ResultAssessmentClientResponseDto returnAssessmentClient = service.evaluatingClients(data.cpf(), data.income());

		return ResponseEntity.ok(returnAssessmentClient);

	}

	@Operation(summary = "Faz uma solicitação de vinculação de um cartão e retorna o número de protocolo da solicitação")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Solicitação feita com sucesso + número de protocolo",
					content = @Content(schema = @Schema(implementation = ProtocolIssueCard.class))) })
	@PreAuthorize("hasAnyAuthority('ADMIN_READ','ADMIN_WRITE')")
	@PostMapping("request-card")
	public ResponseEntity<ProtocolIssueCard> issueCard(@RequestBody IssueCard datas) {
		ProtocolIssueCard protocol = service.requestCardIssuance(datas);
		return ResponseEntity.ok().body(protocol);
	}
}
