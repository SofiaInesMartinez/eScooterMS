package tpe.APIGateway.security;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import tpe.APIGateway.DTO.DTOValidateToken;

import java.util.List;
import java.util.function.Predicate;

/**
 * Authentication pre-filter for API gateway.
 */
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	//FALTA CORREGIR RUTAS!!!
    private static final String _AuthHeader = "Authorization";
    List<String> excludedUrls = List.of("/administration/authenticate", "administration/validate");
    private final WebClient.Builder webClientBuilder;


    public AuthenticationFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply( Config config ) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String bearerToken = request.getHeaders().getFirst( _AuthHeader );

            if( this.isSecured.test( request ) ) {
                return webClientBuilder.build().get()
                        .uri("http://localhost:8005/administration/validate")
                        .header( _AuthHeader, bearerToken )
                        .retrieve().bodyToMono( DTOValidateToken.class )
                        .map( response -> {
                            if( ! response.isAuthenticated() )
                                throw new BadCredentialsException( "JWT invalido" );
                            return exchange;
                        })
                        .flatMap(chain::filter);
            }
            return chain.filter(exchange);
        };
    }

    private final Predicate<ServerHttpRequest> isSecured = request -> excludedUrls.stream().noneMatch(uri -> request.getURI().getPath().contains(uri) );


    /**
     * Required by AbstractGatewayFilterFactory
     */
 
    public static class Config {}

}

