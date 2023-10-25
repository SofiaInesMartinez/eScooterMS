package tpe.tripms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import tpe.tripms.dto.DTOMaintenanceRequest;
import tpe.tripms.dto.DTOScooterStatusRequest;
import tpe.tripms.service.MaintenanceService;

@RestController
@RequestMapping("maintenance")
public class MaintenanceController {
	
	@Autowired(required = true)
	private final MaintenanceService service;
	
	public MaintenanceController(MaintenanceService service) {
		this.service = service;
	}
	
	@PutMapping("/{id}/finish")
	public ResponseEntity<?> finishMaintenance(@PathVariable long id) {
		try {
			return ResponseEntity.ok(service.finishMaintenance(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/scooter/{id}/status")
	public ResponseEntity<?> updateScooterStatus(@PathVariable long id, @RequestBody DTOScooterStatusRequest request) throws WebClientResponseException {
		try {
			return ResponseEntity.ok(service.updateScooterStatus(id, request));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@GetMapping("")
	public ResponseEntity<?> getMaintenance() {
		try {
			return ResponseEntity.ok(service.findAll());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error: Internal server error");
		}
	}
	
	@PostMapping("")
	public ResponseEntity<?> saveMaintenance(@RequestBody  DTOMaintenanceRequest request){
		try {
			return ResponseEntity.ok(service.save(request));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Not found");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMaintenanceById(@PathVariable long id) {
		try {
			return ResponseEntity.ok(service.getMaintenanceById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Not found");
		}
	}

	
	@GetMapping("/byId")
	public ResponseEntity<?> getMaintenancesBySimpleOrdering() {
		try {
			return ResponseEntity.ok(service.getMaintenancesBySimpleOrdering());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error: Internal server error");
		}
	}
}
