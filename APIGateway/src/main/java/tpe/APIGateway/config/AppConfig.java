package tpe.APIGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class AppConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain( ServerHttpSecurity http ) {
        return http
                    .csrf( ServerHttpSecurity.CsrfSpec::disable )
                    .authorizeExchange(exchanges -> exchanges.anyExchange().permitAll() )
                    .build();
    }
    
 /*   
	@Bean
	WebClient.Builder getWebClientBuilder() {
		return WebClient.builder();
	}
	*/
}
