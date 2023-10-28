package tpe.administrationMS.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
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

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;
import tpe.administrationMS.DTO.DTOUserResponse;
import tpe.administrationMS.DTO.DTOUserStatusRequest;
import tpe.administrationMS.DTO.DTOStopRequest;
import tpe.administrationMS.DTO.DTOScooterStatusRequest;
import tpe.administrationMS.DTO.DTOStopResponse;
import tpe.administrationMS.DTO.DTOTariffRequest;
import tpe.administrationMS.DTO.DTOTariffResponse;
import tpe.administrationMS.DTO.DTOScooterByKm;
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

/// USER SERVICES
	
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

	@PutMapping("/user/{id}/status")
	public Mono<ResponseEntity<?>> updateUserStatus(@PathVariable long id, @RequestBody @Valid DTOUserStatusRequest request)
			throws WebClientResponseException {
		return restClient.method(HttpMethod.PUT).uri("http://localhost:8003/user/{id}/status", id)
				.body(BodyInserters.fromValue(request)).retrieve().bodyToMono(DTOUserResponse.class)
				.map(responseBody -> ResponseEntity.status(HttpStatus.OK).body(responseBody));
	}

//// SCOOTER SERVICES
	
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
	public Mono<?> saveScooter(@RequestBody @Valid DTOScooterRequest request) throws WebClientResponseException {
		return restClient.method(HttpMethod.POST).uri("http://localhost:8002/scooter")
				.body(BodyInserters.fromValue(request)).retrieve().bodyToMono(DTOScooterResponse.class)
				.map(responseBody -> ResponseEntity.status(HttpStatus.OK).body(responseBody));
	}

	@PutMapping("/scooter/{id}/status")
	public Mono<?> updateScooterStatus(@PathVariable Long id, @RequestBody @Valid DTOScooterStatusRequest request)
			throws WebClientResponseException {
		return restClient.method(HttpMethod.PUT).uri("http://localhost:8002/scooter/{id}/status", id)
				.body(BodyInserters.fromValue(request)).retrieve().bodyToMono(DTOScooterResponse.class)
				.map(responseBody -> ResponseEntity.status(HttpStatus.OK).body(responseBody));
	}

	@DeleteMapping("scooter/{id}")
	public Mono<?> deleteScooter(@PathVariable Long id) throws WebClientResponseException {
		return restClient.method(HttpMethod.DELETE).uri("http://localhost:8002/scooter/{id}", id).retrieve()
				.bodyToMono(String.class);
	}


////// STOP SERIVCES
	
	@GetMapping("/stop")
	public Mono<ResponseEntity<?>> getStops() {
		try {
			return restClient.method(HttpMethod.GET).uri("http://localhost:8002/stop").retrieve()
					.bodyToFlux(DTOStopResponse.class).collectList()
					.map(responseBody -> ResponseEntity.status(HttpStatus.OK).body(responseBody));
		} catch (Exception e) {
			return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage()));
		}
	}

	@PostMapping("/stop")
	public Mono<?> saveStop(@RequestBody @Valid DTOStopRequest request) throws WebClientResponseException {
		return restClient.method(HttpMethod.POST).uri("http://localhost:8002/stop")
				.body(BodyInserters.fromValue(request)).retrieve().bodyToMono(DTOStopResponse.class)
				.map(responseBody -> ResponseEntity.status(HttpStatus.OK).body(responseBody));
	}

	@DeleteMapping("stop/{id}")
	public Mono<?> deleteStop(@PathVariable Long id) throws WebClientResponseException {
		return restClient.method(HttpMethod.DELETE).uri("http://localhost:8002/stop/{id}", id).retrieve()
				.bodyToMono(String.class);
	}

	
//// TARIFF SERVICES
	
	@GetMapping("/tariff")
	public Mono<ResponseEntity<?>> getTariffs() {
		try {
			return restClient.method(HttpMethod.GET).uri("http://localhost:8002/tariff").retrieve()
					.bodyToFlux(DTOTariffResponse.class).collectList()
					.map(responseBody -> ResponseEntity.status(HttpStatus.OK).body(responseBody));
		} catch (Exception e) {
			return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage()));
		}
	}

	@PostMapping("/tariff")
	public Mono<?> saveTariff(@RequestBody @Valid DTOTariffRequest request) throws WebClientResponseException {
		return restClient.method(HttpMethod.POST).uri("http://localhost:8002/tariff")
				.body(BodyInserters.fromValue(request)).retrieve().bodyToMono(DTOTariffResponse.class)
				.map(responseBody -> ResponseEntity.status(HttpStatus.OK).body(responseBody));
	}

	
//// REPORTS
	
	@GetMapping("/reportByKm")
	public Mono<ResponseEntity<?>> getScootersReportByKm() {
		try {
			return restClient.method(HttpMethod.GET).uri("http://localhost:8002/scooter/reportByKm").retrieve()
					.bodyToFlux(DTOScooterByKm.class).collectList()
					.map(responseBody -> ResponseEntity.status(HttpStatus.OK).body(responseBody));
		} catch (Exception e) {
			return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage()));
		}
	}
	
	@GetMapping("/minimumNumberOfTrips/{number}/year/{year}")
	public Mono<ResponseEntity<?>> getScootersByMinimumNumberOfTrips(@PathVariable int number,@PathVariable int year) {
		try {
			return restClient.method(HttpMethod.GET).uri("http://localhost:8002/scooter/minimumNumberOfTrips/{number}/year/{year}",number,year).retrieve()
					.bodyToFlux(DTOScooterResponse.class).collectList()
					.map(responseBody -> ResponseEntity.status(HttpStatus.OK).body(responseBody));
		} catch (Exception e) {
			return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage()));
		}
	}
	
	@GetMapping("/reportByScooterStatus")
	public Mono<ResponseEntity<?>> getScootersByStatus() {
		try {
			return restClient.method(HttpMethod.GET).uri("http://localhost:8002/scooter/reportByStatus").retrieve()
					.bodyToMono(new ParameterizedTypeReference<Map<String, Long>>() {})
		            .map(responseBody -> ResponseEntity.status(HttpStatus.OK).body(responseBody));
		} catch (Exception e) {
			return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage()));
		}
	}
			

}
