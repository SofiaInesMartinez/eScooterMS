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
 
        return builder.routes()
                .route("administrationMS", r -> r.path("/administration/**" )
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8005"))
                .route("scooterMS", r -> r.path("/scooter/**" )
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8002"))
                .route("userMS", r -> r.path( "/user/**" )
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8005"))
                .route("maintenanceMS", r -> r.path( "/maintenance/**" )
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8004"))
                .build();
    }

}
