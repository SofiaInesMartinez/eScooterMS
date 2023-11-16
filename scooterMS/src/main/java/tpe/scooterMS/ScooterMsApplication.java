package tpe.scooterMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class ScooterMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScooterMsApplication.class, args);
	}

}
