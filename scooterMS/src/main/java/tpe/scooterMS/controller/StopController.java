package tpe.scooterMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tpe.scooterMS.DTO.DTOStopRequest;
import tpe.scooterMS.DTO.DTOStopResponse;
import tpe.scooterMS.exception.NotFoundException;
import tpe.scooterMS.service.StopService;

@RestController
@RequestMapping("stop")
public class StopController {
	
	@Autowired(required = true)
	private final StopService service;
	
	public StopController(StopService service) {
		this.service = service;
	}
	
	@GetMapping("")
	public ResponseEntity<List<DTOStopResponse>> getStop() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping("")
	public ResponseEntity<DTOStopResponse> saveStop(@RequestBody @Valid DTOStopRequest request){
		return ResponseEntity.ok(service.save(request));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DTOStopResponse> getStopById(@PathVariable long id) throws NotFoundException {
		return ResponseEntity.ok(service.getStopById(id));
	}

	
	@GetMapping("/byId")
	public ResponseEntity<List<DTOStopResponse>> getStopsBySimpleOrdering() {
		return ResponseEntity.ok(service.getStopsBySimpleOrdering());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStop(@PathVariable long id) throws NotFoundException {
        service.deleteStop(id);
        return ResponseEntity.ok("Stop con ID " + id + " eliminada con éxito.");
	}
}
