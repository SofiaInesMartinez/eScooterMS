package tpe.administrationMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;
import tpe.administrationMS.DTO.DTOUserResponse;
import tpe.administrationMS.DTO.DTOScooterStatusRequest;
import tpe.administrationMS.DTO.DTOScooterRequest;
import tpe.administrationMS.DTO.DTOScooterResponse;

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
			return restClient.method(HttpMethod.GET).uri("http://localhost:8003/user").retrieve()
					.bodyToFlux(DTOUserResponse.class).collectList()
					.map(responseBody -> ResponseEntity.status(HttpStatus.OK).body(responseBody));
		} catch (Exception e) {
			return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage()));
		}
	}

	@PutMapping("/user/{id}/status/{status}")
	public Mono<ResponseEntity<?>> updateUserStatus(@PathVariable Long id, @PathVariable Integer status) {
		try {
			return restClient.method(HttpMethod.PUT).uri("http://localhost:8003/user/{id}/status/{status}", id, status)
					.retrieve().bodyToMono(DTOUserResponse.class).map(responseBody -> {
						HttpHeaders headers = new HttpHeaders();
						headers.add("Custom-Header", "Custom-Value");
						return ResponseEntity.status(HttpStatus.OK).headers(headers).body(responseBody);
					});
		} catch (Exception e) {
			return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage()));
		}
	}

	@GetMapping("/scooter")
	public Mono<ResponseEntity<?>> getScooters() {
		try {
			return restClient.method(HttpMethod.GET).uri("http://localhost:8002/scooter").retrieve()
					.bodyToFlux(DTOScooterResponse.class).collectList()
					.map(responseBody -> ResponseEntity.status(HttpStatus.OK).body(responseBody));
		} catch (Exception e) {
			return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage()));
		}
	}

	@PostMapping("/scooter")
	public Mono<?> saveScooter(@RequestBody DTOScooterRequest request) throws WebClientResponseException {
		return restClient.method(HttpMethod.POST).uri("http://localhost:8002/scooter")
				.body(BodyInserters.fromValue(request)).retrieve().bodyToMono(DTOScooterResponse.class)
				.map(responseBody -> ResponseEntity.status(HttpStatus.OK).body(responseBody));
	}

	@PutMapping("/scooter/{id}/status")
	public Mono<?> updateScooterStatus(@PathVariable Long id, @RequestBody DTOScooterStatusRequest request)
			throws WebClientResponseException {
		return restClient.method(HttpMethod.PUT).uri("http://localhost:8002/scooter/{id}/status", id)
				.body(BodyInserters.fromValue(request)).retrieve().bodyToMono(DTOScooterResponse.class)
				.map(responseBody -> ResponseEntity.status(HttpStatus.OK).body(responseBody));
	}
	
	
	@DeleteMapping("scooter/{id}")
    public Mono<?> deleteScooter(@PathVariable Long id) throws WebClientResponseException{
        return restClient.method(HttpMethod.DELETE)
                .uri("http://localhost:8002/scooter/{id}", id)
                .retrieve().bodyToMono(String.class);
    }
	
}
