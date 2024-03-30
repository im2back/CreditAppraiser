package io.github.im2back.credit.appraiser.infra.config;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import io.github.im2back.credit.appraiser.infra.exceptions.CustomAccessDeniedHandler;
import io.github.im2back.credit.appraiser.infra.exceptions.CustomAuthenticationEntryPoint;
import org.springframework.http.HttpMethod;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Value("${keycloak.auth-server-url}")
	private String keycloakServerUrl;

		
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeRequests(auth ->{
		auth.antMatchers(HttpMethod.POST,"/credit-appraiser/request-card").hasAnyAuthority("ADMIN_READ","ADMIN_WRITE");	
		auth.anyRequest().authenticated();
		})
		.oauth2ResourceServer(oauth2-> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())))
		  .exceptionHandling()
          .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
          .accessDeniedHandler(new CustomAccessDeniedHandler());
		return http.build();
	}
	
	@Bean
	public JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withJwkSetUri(keycloakServerUrl+"/protocol/openid-connect/certs").build();
	}
	
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverterForKeycloak() {
		 Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = jwt ->{
				Map<String,Object> resourceAcess = jwt.getClaim("realm_access");
				Collection<String> roles = (Collection<String>) resourceAcess.get("roles");
				return roles.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
			};			
				 JwtAuthenticationConverter jwtAuthenticationConverter = new  JwtAuthenticationConverter();
				 jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
				 return jwtAuthenticationConverter;
	 }

}