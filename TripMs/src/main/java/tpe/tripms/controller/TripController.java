package tpe.tripms.controller;

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

import jakarta.validation.Valid;
import tpe.tripms.dto.TripRequestDTO;
import tpe.tripms.dto.TripResponseDTO;
import tpe.tripms.service.TripService;

@RestController
@RequestMapping("/trip")
public class TripController {

	@Autowired
	private TripService service;
	
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
	public ResponseEntity<TripResponseDTO> saveTrip(@RequestBody @Valid TripRequestDTO request) {
		return new ResponseEntity<>(service.saveTrip(request), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<TripResponseDTO> deleteTrip(@PathVariable int id) throws Exception {
		return ResponseEntity.ok(service.deleteTrip(id));
	}
}
