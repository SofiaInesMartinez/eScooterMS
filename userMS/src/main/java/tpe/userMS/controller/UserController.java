package tpe.userMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tpe.userMS.DTO.DTOUserRequest;
import tpe.userMS.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired(required = true)
	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}
	
	 
	@GetMapping("")
	public ResponseEntity<?> getUsers() {
		try {
			return ResponseEntity.ok(service.findAll());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error: Internal server error");
		}
	}

	@PostMapping("")
	public ResponseEntity<?> saveUser(@RequestBody @Valid DTOUserRequest request) {
		try {
			return ResponseEntity.ok(service.save(request));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: Failed to save");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable long id) {
		try {
			return ResponseEntity.ok(service.getUserById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Not found");
		}
	}

	
	@GetMapping("/byCreatedAt")
	public ResponseEntity<?> getUsersBySimpleOrdering() {
		try {
			return ResponseEntity.ok(service.getUsersBySimpleOrdering());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error: Internal server error");
		}
	}

}
