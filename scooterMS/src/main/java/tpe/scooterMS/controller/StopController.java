package tpe.scooterMS.controller;

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

import tpe.scooterMS.DTO.DTOStopRequest;
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
	public ResponseEntity<?> getStop() {
		try {
			return ResponseEntity.ok(service.findAll());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error: Internal server error");
		}
	}
	
	@PostMapping("")
	public ResponseEntity<?> saveStop(@RequestBody  DTOStopRequest request){
		try {
			return ResponseEntity.ok(service.save(request));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Not found");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getStopById(@PathVariable long id) {
		try {
			return ResponseEntity.ok(service.getStopById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Not found");
		}
	}

	
	@GetMapping("/byId")
	public ResponseEntity<?> getStopsBySimpleOrdering() {
		try {
			return ResponseEntity.ok(service.getStopsBySimpleOrdering());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error: Internal server error");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteScooter(@PathVariable long id
	) {
	    try {
	        service.deleteStop(id);
	        return ResponseEntity.ok("Stop con ID " + id + " eliminada con éxito.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Error interno del servidor");
	    }
	}
}
