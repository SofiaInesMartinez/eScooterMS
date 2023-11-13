package tpe.APIGateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

//VER SI QUEDA ESTA CLASE O Route.reouteConfig

/*@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/administration/authenticate",
            "/administration/encode"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
*/