package tpe.scooterMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tpe.scooterMS.DTO.TariffRequestDTO;
import tpe.scooterMS.DTO.TariffResponseDTO;
import tpe.scooterMS.exception.NotFoundException;
import tpe.scooterMS.model.Roles;
import tpe.scooterMS.service.TariffService;

@RestController
@RequestMapping("/tariff")
public class TariffController {
	
	@Autowired
	private TariffService service;
	
	@GetMapping("")
	@PreAuthorize("hasAnyAuthority('" + Roles.ADMIN + "', '" + Roles.USER + "')")
	public ResponseEntity<List<TariffResponseDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize( "hasAnyAuthority(\"" + Roles.ADMIN + "\" )" )
	public ResponseEntity<TariffResponseDTO> findById(@PathVariable int id) throws NotFoundException {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping("")
	@PreAuthorize( "hasAnyAuthority(\"" + Roles.ADMIN + "\" )" )
	public ResponseEntity<TariffResponseDTO> saveTariff(@RequestBody @Valid TariffRequestDTO request) {
		return new ResponseEntity<>(service.saveTariff(request), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize( "hasAnyAuthority(\"" + Roles.ADMIN + "\" )" )
	public ResponseEntity<TariffResponseDTO> deleteTariff(@PathVariable int id) throws NotFoundException {
		return ResponseEntity.ok(service.deleteTariff(id));
	}
}
