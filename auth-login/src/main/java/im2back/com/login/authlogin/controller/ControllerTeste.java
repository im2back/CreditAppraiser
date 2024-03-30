package im2back.com.login.authlogin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teste")
public class ControllerTeste {

	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADMIN_READ','ADMIN_WRITE')")
	public ResponseEntity<String> teste(){
		return ResponseEntity.ok("Conectado");
	}
	
}
