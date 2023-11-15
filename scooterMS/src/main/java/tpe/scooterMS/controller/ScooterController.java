package tpe.scooterMS.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tpe.scooterMS.DTO.DTOCoordinatesRequest;
import tpe.scooterMS.DTO.DTOScooterRequest;
import tpe.scooterMS.DTO.DTOScooterResponse;
import tpe.scooterMS.DTO.DTOScooterStatusRequest;
import tpe.scooterMS.DTO.ScooterByKmsDTO;
import tpe.scooterMS.DTO.ScooterByTimeDTO;
import tpe.scooterMS.DTO.ScooterByTimePauseDTO;
import tpe.scooterMS.exception.NotFoundException;
import tpe.scooterMS.model.Roles;
import tpe.scooterMS.service.ScooterService;

@RestController
@RequestMapping("scooter")
public class ScooterController {
	
	@Autowired(required = true)
	private final ScooterService service;
	
	public ScooterController(ScooterService service) {
		this.service = service;
	}
	
	@PutMapping("/{id}/coordinates")
	public ResponseEntity<DTOScooterResponse> updateScooterCoordinates(@PathVariable long id, @RequestBody @Valid DTOCoordinatesRequest request) throws NotFoundException {
		return ResponseEntity.ok(service.updateScooterCoordinates(id, request));
	}
	
	@GetMapping("/latitude/{latitude}/longitude/{longitude}")
	public ResponseEntity<List<DTOScooterResponse>> getNearbyScooters(@PathVariable double latitude, @PathVariable double longitude ) {
		return ResponseEntity.ok(service.getNearbyScooters(latitude, longitude));
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<DTOScooterResponse> updateScooterStatus(@PathVariable long id, @RequestBody @Valid DTOScooterStatusRequest request) throws NotFoundException {
		return ResponseEntity.ok(service.updateScooterStatus(id, request));
	}
	
	@GetMapping("")
	public ResponseEntity<List<DTOScooterResponse>> getScooters() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping("")
	@PreAuthorize("hasAnyAuthority(" + Roles.ADMIN + ")")
	public ResponseEntity<DTOScooterResponse> saveScooter(@RequestBody @Valid DTOScooterRequest request){
		return ResponseEntity.ok(service.save(request));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DTOScooterResponse> getScooterById(@PathVariable long id) throws NotFoundException {
		return ResponseEntity.ok(service.getScooterById(id));
	}

	
	@GetMapping("/byId")
	@PreAuthorize("hasAnyAuthority(" + Roles.ADMIN + ")")
	public ResponseEntity<List<DTOScooterResponse>> getScootersBySimpleOrdering() {
		return ResponseEntity.ok(service.getScootersBySimpleOrdering());
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority(" + Roles.ADMIN + ")")
	public ResponseEntity<String> deleteScooter(@PathVariable long id) throws NotFoundException {
        service.deleteScooter(id);
        return ResponseEntity.ok("Scooter con ID " + id + " eliminado con éxito.");
	}
	
	@GetMapping("/reportByKm")
	@PreAuthorize("hasAnyAuthority(" + Roles.ADMIN + ")")
	public ResponseEntity<List<ScooterByKmsDTO>> getScootersReportByKm() {
		return ResponseEntity.ok(service.getScootersReportByKm());
	}
	
	@GetMapping("/reportByTotalTime")
	@PreAuthorize("hasAnyAuthority(" + Roles.ADMIN + ")")
	public ResponseEntity<List<ScooterByTimePauseDTO>> getScootersReportByTotalTime() {
		return ResponseEntity.ok(service.getScootersReportByTotalTime());
	}
	
	@GetMapping("/reportByTimeWithPauses")
	@PreAuthorize("hasAnyAuthority(" + Roles.ADMIN + ")")
	public ResponseEntity<List<ScooterByTimeDTO>> getScootersReportByTimeWithPauses() {
		return ResponseEntity.ok(service.getScootersReportByTimeWithPauses());
	}
	
	@GetMapping("/minimumNumberOfTrips/{number}/year/{year}")
	@PreAuthorize("hasAnyAuthority(" + Roles.ADMIN + ")")
	public ResponseEntity<List<DTOScooterResponse>> getScootersByMinimumNumberOfTrips(@PathVariable int number,@PathVariable int year) {
		return ResponseEntity.ok(service.getScootersByMinimumNumberOfTrips(number,year));
	}
	
	@GetMapping("/reportByStatus")
	@PreAuthorize("hasAnyAuthority(" + Roles.ADMIN + ")")
	public ResponseEntity<Map<String, Long>> getScootersByStatus() {
		return ResponseEntity.ok(service.getScootersByStatus());
	}
}
