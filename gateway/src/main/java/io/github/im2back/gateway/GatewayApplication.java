package io.github.im2back.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@Bean
	RouteLocator routes(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route(r -> r.path("/clients/**").uri("lb://ms-clients"))
				.route(r -> r.path("/cards/**").uri("lb://ms-cards"))
				.build();
	}

}
