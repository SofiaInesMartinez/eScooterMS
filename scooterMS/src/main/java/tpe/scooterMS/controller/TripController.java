package tpe.scooterMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.reactive.function.client.WebClientResponseException;

import jakarta.validation.Valid;
import tpe.scooterMS.DTO.DTOInvoicedAmountResponse;
import tpe.scooterMS.DTO.TripRequestDTO;
import tpe.scooterMS.DTO.TripResponseDTO;
import tpe.scooterMS.service.TripService;

@RestController
@RequestMapping("/trip")
public class TripController {

	@Autowired
	private TripService service;
	
//	@PutMapping("/{id}/endPause")
//	public ResponseEntity<TripResponseDTO> endPause(@PathVariable int id) throws Exception {
//		return ResponseEntity.ok(service.endPause(id));
//	}
//	
//	@PutMapping("/{id}/startPause")
//	public ResponseEntity<TripResponseDTO> startPause(@PathVariable int id) throws Exception {
//		return ResponseEntity.ok(service.startPause(id));
//	}
	
	@GetMapping("/year/{year}/fromMonth/{month1}/ToMonth/{month2}")
	public ResponseEntity<?> getInvoicedAmountByYearAndMonthRange(@PathVariable int year, @PathVariable int month1, @PathVariable int month2) {
		try {
			return ResponseEntity.ok(service.getInvoicedAmountByYearAndMonthRange(year, month1, month2));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}/end")
	public ResponseEntity<TripResponseDTO> endTrip(@PathVariable int id) throws Exception {
		return ResponseEntity.ok(service.endTrip(id));
	}
	
	@GetMapping("")
	public ResponseEntity<List<TripResponseDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TripResponseDTO> findById(@PathVariable int id) throws Exception {
		return ResponseEntity.ok(service.findByid(id));
	}
	
	@PostMapping("")
	public ResponseEntity<TripResponseDTO> saveTrip(@RequestBody @Valid TripRequestDTO request) throws Exception, WebClientResponseException {
		return new ResponseEntity<>(service.saveTrip(request), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<TripResponseDTO> deleteTrip(@PathVariable int id) throws Exception {
		return ResponseEntity.ok(service.deleteTrip(id));
	}
}
