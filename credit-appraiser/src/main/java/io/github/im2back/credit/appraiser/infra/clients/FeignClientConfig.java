package io.github.im2back.credit.appraiser.infra.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;

@Configuration
public class FeignClientConfig {

	@Bean
	public RequestInterceptor requestInterceptor() {
	    return template -> {
	        // Obter o token armazenado
	        String token = TokenStorage.getToken();
	        if (token != null) {
	            // Adicionar o token de autenticação ao cabeçalho da requisição Feign
	            template.header("Authorization", token);
	        }
	    };
	}
}
