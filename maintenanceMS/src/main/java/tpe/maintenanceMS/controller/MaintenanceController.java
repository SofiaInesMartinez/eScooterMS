package tpe.maintenanceMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tpe.maintenanceMS.dto.DTOMaintenanceRequest;
import tpe.maintenanceMS.dto.DTOScooterStatusRequest;
import tpe.maintenanceMS.exception.MaintenanceAlreadyFinishedException;
import tpe.maintenanceMS.exception.NotFoundException;
import tpe.maintenanceMS.exception.ScooterAlreadyInMaintenanceException;
import tpe.maintenanceMS.exception.ServiceCommunicationException;
import tpe.maintenanceMS.model.Roles;
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
	@PreAuthorize( "hasAnyAuthority(\"" + Roles.ADMIN + "\" )" )
	public ResponseEntity<?> getScootersReportByTimeWithPauses(@RequestHeader("Authorization") String authorizationHeader) throws ServiceCommunicationException {
		return ResponseEntity.ok(service.getScootersReportByTimeWithPauses(authorizationHeader));
	}
	
	@GetMapping("/reportByTotalTime")
	@PreAuthorize( "hasAnyAuthority(\"" + Roles.ADMIN + "\" )" )
	public ResponseEntity<?> getScootersReportByTotalTime(@RequestHeader("Authorization") String authorizationHeader) throws ServiceCommunicationException {
		return ResponseEntity.ok(service.getScootersReportByTotalTime(authorizationHeader));
	}
	
	@PutMapping("/{id}/finish")
	@PreAuthorize( "hasAnyAuthority(\"" + Roles.ADMIN + "\" )" )
	public ResponseEntity<?> finishMaintenance(@RequestHeader("Authorization") String authorizationHeader, @PathVariable long id) throws NotFoundException, MaintenanceAlreadyFinishedException, ServiceCommunicationException {
		return ResponseEntity.ok(service.finishMaintenance(authorizationHeader, id));
	}
	
	@PutMapping("/scooter/{id}/status")
	@PreAuthorize( "hasAnyAuthority(\"" + Roles.ADMIN + "\" )" )
	public ResponseEntity<?> updateScooterStatus(@RequestHeader("Authorization") String authorizationHeader, @PathVariable long id, @RequestBody @Valid DTOScooterStatusRequest request) throws NotFoundException {
		return ResponseEntity.ok(service.updateScooterStatus(authorizationHeader, id, request));
	}
	
	@GetMapping("")
	@PreAuthorize( "hasAnyAuthority(\"" + Roles.ADMIN + "\" )" )
	public ResponseEntity<?> getMaintenance() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping("")
	@PreAuthorize( "hasAnyAuthority(\"" + Roles.ADMIN + "\" )" )
	public ResponseEntity<?> saveMaintenance(@RequestHeader("Authorization") String authorizationHeader, @RequestBody @Valid DTOMaintenanceRequest request) throws NotFoundException, ScooterAlreadyInMaintenanceException{
		return ResponseEntity.ok(service.save(authorizationHeader, request));
	}
	
	@GetMapping("/{id}")
	@PreAuthorize( "hasAnyAuthority(\"" + Roles.ADMIN + "\" )" )
	public ResponseEntity<?> getMaintenanceById(@PathVariable long id) throws NotFoundException {
		return ResponseEntity.ok(service.getMaintenanceById(id));
	}

	
	@GetMapping("/byId")
	@PreAuthorize( "hasAnyAuthority(\"" + Roles.ADMIN + "\" )" )
	public ResponseEntity<?> getMaintenancesBySimpleOrdering() {
		return ResponseEntity.ok(service.getMaintenancesBySimpleOrdering());
	}
}
