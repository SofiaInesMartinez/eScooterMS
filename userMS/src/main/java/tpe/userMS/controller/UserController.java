package tpe.userMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import tpe.userMS.DTO.DTOAccountResponse;
import tpe.userMS.DTO.DTOAccountUserStatusResponse;
import tpe.userMS.DTO.DTOScooterResponse;
import tpe.userMS.DTO.DTOUserRequest;
import tpe.userMS.DTO.DTOUserResponse;
import tpe.userMS.DTO.DTOUserStatusRequest;
import tpe.userMS.exception.AccountWithoutMoneyException;
import tpe.userMS.exception.DisabledUserException;
import tpe.userMS.exception.NotFoundException;
import tpe.userMS.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired(required = true)
	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping("/scooter/latitude/{latitude}/longitude/{longitude}")
	public ResponseEntity<List<DTOScooterResponse>> getNearbyScooters(@PathVariable double latitude, @PathVariable double longitude) {
		return ResponseEntity.ok(service.getNearbyScooters(latitude, longitude));
	}
	
	@GetMapping("/{id}/accounts")
	public ResponseEntity<List<DTOAccountResponse>> getUserAccounts(@PathVariable long id) throws NotFoundException {
		return ResponseEntity.ok(service.getUserAccounts(id));
	}
	
	@GetMapping("/{id}/account/withBalance")
	public ResponseEntity<DTOAccountUserStatusResponse> getAccountByUserIdWithBalance(@PathVariable long id) throws NotFoundException, AccountWithoutMoneyException {
		return ResponseEntity.ok(service.getAccountByUserIdWithBalance(id));
	}

	@GetMapping("")
	public ResponseEntity<List<DTOUserResponse>> getUsers() {
		return ResponseEntity.ok(service.findAll());
	}

	@PostMapping("")
	public ResponseEntity<DTOUserResponse> saveUser(@RequestBody @Valid DTOUserRequest request) {
		return ResponseEntity.ok(service.save(request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id) throws NotFoundException {
		service.delete(id);
		return ResponseEntity.ok("The user with id " + id + " has been succesfully deleted."); 
	}
	
	@PutMapping("/{id}/status")
    public ResponseEntity<DTOUserResponse> updateUserStatus(@PathVariable long id, @RequestBody @Valid DTOUserStatusRequest request) throws NotFoundException {
        DTOUserResponse updatedUser = service.updateStatus(id, request);
        return ResponseEntity.ok(updatedUser);
    }
	
	@PostMapping("/{id}/addAccount/{accountId}")
    public ResponseEntity<String> addAccountToUser(@PathVariable Long id, @PathVariable Long accountId) throws NotFoundException, DisabledUserException {
        service.addAccountToUser(id, accountId);
        return ResponseEntity.ok("Added account " + accountId + " to user " +id);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<DTOUserResponse> getUserById(@PathVariable long id) throws NotFoundException {
			return ResponseEntity.ok(service.getUserById(id));
	}

	@GetMapping("/byCreatedAt")
	public ResponseEntity<List<DTOUserResponse>> getUsersBySimpleOrdering() {
		return ResponseEntity.ok(service.getUsersBySimpleOrdering());
	}

}
