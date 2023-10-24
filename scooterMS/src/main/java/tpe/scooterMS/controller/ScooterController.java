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

import tpe.scooterMS.DTO.DTOScooterRequest;
import tpe.scooterMS.service.ScooterService;

@RestController
@RequestMapping("scooter")
public class ScooterController {
	
	@Autowired(required = true)
	private final ScooterService service;
	
	public ScooterController(ScooterService service) {
		this.service = service;
	}
	
	@GetMapping("")
	public ResponseEntity<?> getScooters() {
		try {
			return ResponseEntity.ok(service.findAll());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error: Internal server error");
		}
	}
	
	@PostMapping("")
	public ResponseEntity<?> saveScooter(@RequestBody  DTOScooterRequest request){
		try {
			return ResponseEntity.ok(service.save(request));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Not found");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getScooterById(@PathVariable long id) {
		try {
			return ResponseEntity.ok(service.getScooterById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Not found");
		}
	}

	
	@GetMapping("/byId")
	public ResponseEntity<?> getScootersBySimpleOrdering() {
		try {
			return ResponseEntity.ok(service.getScootersBySimpleOrdering());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error: Internal server error");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteScooter(@PathVariable long idScooter
	) {
	    try {
	        service.deleteScooter(idScooter);
	        return ResponseEntity.ok("Scooter con ID " + idScooter + " eliminado con éxito.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Error interno del servidor");
	    }
	}
}
