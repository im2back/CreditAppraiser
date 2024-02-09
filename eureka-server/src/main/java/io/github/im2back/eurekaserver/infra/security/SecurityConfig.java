package io.github.im2back.eurekaserver.infra.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("cursoms-eureka")
		.password("{noop}12345678")
		.roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .csrf().disable()
	        .authorizeRequests()
	            .antMatchers("/v3/api-docs/**", "swagger-ui.html", "/swagger-ui/**").permitAll() // Permitir acesso sem autenticação
	            .anyRequest().authenticated() // Todas as outras requisições precisam de autenticação
	        .and()
	        .httpBasic(); // Usar autenticação básica HTTP
	}


}
