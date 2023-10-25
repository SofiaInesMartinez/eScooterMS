package tpe.administrationMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

@RestController
@RequestMapping("administration")
public class administrationController {

	@Autowired
	private final WebClient restClient;

	public administrationController(@Qualifier("restClient") WebClient restClient) {
		this.restClient = restClient;
	}

	@GetMapping("/user")
	public ResponseEntity<?> getUsers() {
		try {
			Mono<ResponseEntity<String>> result = restClient.method(HttpMethod.GET).uri("http://localhost:8003/user")
					.retrieve().toEntity(String.class);

			result.subscribe(responseEntity -> {
				System.out.println("Response status: " + responseEntity.getStatusCode());
				System.out.println("Response body: " + responseEntity.getBody());
			});
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error: Internal server error");
		}
	}

	@PutMapping("/user/{id}/status/{status}")
	public ResponseEntity<?> updateUserStatus(@PathVariable Long id, @PathVariable Integer status) {
		try {
			Mono<ResponseEntity<String>> result = restClient.method(HttpMethod.PUT)
					.uri("http://localhost:8003/user/{id}/status/{status}", id, status).retrieve()
					.toEntity(String.class);

			result.subscribe(responseEntity -> {
				System.out.println("Response status: " + responseEntity.getStatusCode());
				System.out.println("Response body: " + responseEntity.getBody());
			});
			return ResponseEntity.ok("User status with ID " + id + " has been updated to " + status);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}
}
