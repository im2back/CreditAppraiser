package im2back.com.login.authlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AuthLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthLoginApplication.class, args);
	}

}
