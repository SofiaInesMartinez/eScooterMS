package tpe.maintenanceMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tpe.maintenanceMS.dto.DTOMaintenanceRequest;
import tpe.maintenanceMS.dto.DTOScooterStatusRequest;
import tpe.maintenanceMS.exception.MaintenanceAlreadyFinishedException;
import tpe.maintenanceMS.exception.NotFoundException;
import tpe.maintenanceMS.exception.ScooterAlreadyInMaintenanceException;
import tpe.maintenanceMS.exception.ServiceCommunicationException;
import tpe.maintenanceMS.service.MaintenanceService;

@RestController
@RequestMapping("maintenance")
public class MaintenanceController {
	
	@Autowired(required = true)
	private final MaintenanceService service;
	
	public MaintenanceController(MaintenanceService service) {
		this.service = service;
	}
	
	@GetMapping("/reportByTimeWithPauses")
	public ResponseEntity<?> getScootersReportByTimeWithPauses() throws ServiceCommunicationException {
		return ResponseEntity.ok(service.getScootersReportByTimeWithPauses());
	}
	
	@GetMapping("/reportByTotalTime")
	public ResponseEntity<?> getScootersReportByTotalTime() throws ServiceCommunicationException {
		return ResponseEntity.ok(service.getScootersReportByTotalTime());
	}
	
	@PutMapping("/{id}/finish")
	public ResponseEntity<?> finishMaintenance(@PathVariable long id) throws NotFoundException, MaintenanceAlreadyFinishedException, ServiceCommunicationException {
		return ResponseEntity.ok(service.finishMaintenance(id));
	}
	
	@PutMapping("/scooter/{id}/status")
	public ResponseEntity<?> updateScooterStatus(@PathVariable long id, @RequestBody @Valid DTOScooterStatusRequest request) throws NotFoundException {
		return ResponseEntity.ok(service.updateScooterStatus(id, request));
	}
	
	@GetMapping("")
	public ResponseEntity<?> getMaintenance() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping("")
	public ResponseEntity<?> saveMaintenance(@RequestBody @Valid DTOMaintenanceRequest request) throws NotFoundException, ScooterAlreadyInMaintenanceException{
		return ResponseEntity.ok(service.save(request));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMaintenanceById(@PathVariable long id) throws NotFoundException {
		return ResponseEntity.ok(service.getMaintenanceById(id));
	}

	
	@GetMapping("/byId")
	public ResponseEntity<?> getMaintenancesBySimpleOrdering() {
		return ResponseEntity.ok(service.getMaintenancesBySimpleOrdering());
	}
}
