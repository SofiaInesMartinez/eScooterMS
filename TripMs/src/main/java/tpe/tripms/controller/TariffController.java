package tpe.tripms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tpe.tripms.dto.TariffRequestDTO;
import tpe.tripms.dto.TariffResponseDTO;
import tpe.tripms.service.TariffService;

@RestController
@RequestMapping("/tariff")
public class TariffController {
	
	@Autowired
	private TariffService service;
	
	@GetMapping("")
	public ResponseEntity<List<TariffResponseDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TariffResponseDTO> findById(@PathVariable int id) throws Exception {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping("")
	public ResponseEntity<TariffResponseDTO> saveTariff(@RequestBody @Valid TariffRequestDTO request) {
		return new ResponseEntity<>(service.saveTariff(request), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<TariffResponseDTO> deleteTariff(@PathVariable int id) throws Exception {
		return ResponseEntity.ok(service.deleteTariff(id));
	}
}
