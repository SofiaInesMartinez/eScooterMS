package tpe.userMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@DeleteMapping("/{id}")
	public ResponseEntity<?>  deleteUser(@PathVariable long id) {
		try {
			service.delete(id);
			return ResponseEntity.ok("The user with id " + id + " has been succesfully deleted."); 
		} catch (Exception e) {	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Not found");
		}
	}
	
	@PutMapping("/{id}/status/{status}")
    public ResponseEntity<?> updateUserStatus(@PathVariable long id, @PathVariable String status) {
        try {
            service.updateStatus(id, status);
            return ResponseEntity.ok("User status with ID " + id + " has been updated to " + status);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
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
