package im2back.com.login.authlogin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import im2back.com.login.authlogin.model.User;
import im2back.com.login.authlogin.service.AuthService;

@RequestMapping("login")
@RestController
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	
	@PostMapping
	public ResponseEntity<?> login(@Valid @RequestBody User user){
		return service.login(user);
	}
	
	@PostMapping(value = "refresh")
	public ResponseEntity<?> login(@RequestParam("refresh_token") String refreshToken ){
		return service.refreshToken(refreshToken);
	}
	

	
	
}
