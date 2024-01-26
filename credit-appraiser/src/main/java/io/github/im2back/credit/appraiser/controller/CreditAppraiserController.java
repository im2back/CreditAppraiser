package io.github.im2back.credit.appraiser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.im2back.credit.appraiser.model.ClientSituation;
import io.github.im2back.credit.appraiser.service.CreditApraiserService;

@RestController
@RequestMapping("credit-appraiser")
public class CreditAppraiserController {

	@Autowired
	private CreditApraiserService service;
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value="client-status",params="cpf")
	public ResponseEntity checkStatusClient(@RequestParam("cpf") String cpf) {
		ClientSituation customerSituation;
	
			customerSituation = service.getClientSituation(cpf);
			return ResponseEntity.ok(customerSituation);
			

			}
}
