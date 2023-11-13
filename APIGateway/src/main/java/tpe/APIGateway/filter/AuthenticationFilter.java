package tpe.APIGateway.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import tpe.APIGateway.util.JwtUtil;

//PARA REUBICAR CLASE. NO SE USARÍA EN EL API GATEWAY 

/*
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	
    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
					// Validate the token
					jwtUtil.validateToken(authHeader);
					
					// Extract roles from the token
					List<String> userRoles = jwtUtil.extractRoles(authHeader);
					 
					// Check if the user has the required role (e.g., "ADMIN")
					if (!config.getRoles().isEmpty()) {
						if (userRoles.isEmpty()) {
							throw new RuntimeException("User does not have the required role for access");
						}
						
						boolean containsRequiredRole = false;
						int i = 0;
						while (!containsRequiredRole && i < userRoles.size()) {
							if (config.getRoles().contains(userRoles.get(i))) {
								containsRequiredRole = true;
							}
							i++;
						}
						
						if (!containsRequiredRole) {
							throw new RuntimeException("User does not have the required role for access");
						}
					}
                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("unauthorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {
    	private String roles = ""; // Valor por defecto si no se especifica un valor en el application.yml

		public String getRoles() {
			return roles;
		}

		public void setRoles(String roles) {
			this.roles = roles;
		}
    }
}*/
