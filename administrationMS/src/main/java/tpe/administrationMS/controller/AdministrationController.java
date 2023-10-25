package tpe.administrationMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import tpe.administrationMS.DTO.DTOUserResponse;

@RestController
@RequestMapping("administration")
public class AdministrationController {

	@Autowired
	private final WebClient restClient;

	public AdministrationController(@Qualifier("restClient") WebClient restClient) {
		this.restClient = restClient;
	}
	
	
	@GetMapping("/user")
	public Mono<ResponseEntity<?>> getUsers() {
	    try {
	        // Update the URI and other parameters as necessary
	        return restClient
	                .method(HttpMethod.GET)
	                .uri("http://localhost:8003/user")
	                .retrieve()
	                .bodyToFlux(DTOUserResponse.class)
	                .collectList()
	                .map(responseBody -> ResponseEntity.status(HttpStatus.OK).body(responseBody));
	    } catch (Exception e) {
	        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error: " + e.getMessage()));
	    }
	}
	

	@PutMapping("/user/{id}/status/{status}")
	public Mono<ResponseEntity<?>> updateUserStatus(@PathVariable Long id, @PathVariable Integer status) {
	    try {
	        // Update the URI and other parameters as necessary
	        return restClient
	                .method(HttpMethod.PUT)
	                .uri("http://localhost:8003/user/{id}/status/{status}", id, status)
	                .retrieve()
	                .bodyToMono(DTOUserResponse.class)
	                .map(responseBody -> {
	                    HttpHeaders headers = new HttpHeaders();
	                    headers.add("Custom-Header", "Custom-Value");
	                    return ResponseEntity.status(HttpStatus.OK).headers(headers).body(responseBody);
	                });
	    } catch (Exception e) {
	        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error: " + e.getMessage()));
	    }
	}
}
