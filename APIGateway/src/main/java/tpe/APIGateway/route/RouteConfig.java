package tpe.APIGateway.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tpe.APIGateway.security.AuthenticationFilter;

@Configuration
public class RouteConfig {

    @Bean
    RouteLocator routes( RouteLocatorBuilder builder, AuthenticationFilter authFilter ) {
    	//FALTA CORREGIR RUTAS!!
        return builder.routes()
                .route("lll", r -> r.path("/api/authenticate" )
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8080"))
                .route("auth-service", r -> r.path("/api/register" )
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8080"))
                .route("micro-a-product", r -> r.path( "/api/admin/products/**" )
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8002"))
                .route("micro-a-product", r -> r.path("/api/products/**")
                        .filters(f ->
                            f.filter(authFilter.apply(new AuthenticationFilter.Config()))
                        )
                        .uri("http://localhost:8002"))
                .build();
    }

}
