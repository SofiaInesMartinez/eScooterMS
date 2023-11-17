package tpe.administrationMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tpe.administrationMS.DTO.DTOAccountResponse;
import tpe.administrationMS.DTO.DTOAccountUserStatusResponse;
import tpe.administrationMS.DTO.DTOScooterResponse;
import tpe.administrationMS.DTO.DTOUserRequest;
import tpe.administrationMS.DTO.DTOUserResponse;
import tpe.administrationMS.DTO.DTOUserStatusRequest;
import tpe.administrationMS.exception.AccountWithoutMoneyException;
import tpe.administrationMS.exception.DisabledUserException;
import tpe.administrationMS.exception.InvalidRolesRequestException;
import tpe.administrationMS.exception.NotFoundException;
import tpe.administrationMS.exception.UserWithEmailAlreadyExistsException;
import tpe.administrationMS.model.Roles;
import tpe.administrationMS.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired(required = true)
	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping("/scooter/latitude/{latitude}/longitude/{longitude}")
	@PreAuthorize("hasAnyAuthority('" + Roles.ADMIN + "', '" + Roles.USER + "')")
	public ResponseEntity<List<DTOScooterResponse>> getNearbyScooters(@RequestHeader("Authorization") String authorizationHeader, @PathVariable double latitude, @PathVariable double longitude) {
		return ResponseEntity.ok(service.getNearbyScooters(authorizationHeader, latitude, longitude));
	}
	
	@GetMapping("/{id}/accounts")
	@PreAuthorize("hasAnyAuthority('" + Roles.ADMIN + "', '" + Roles.USER + "')")
	public ResponseEntity<List<DTOAccountResponse>> getUserAccounts(@PathVariable long id) throws NotFoundException {
		return ResponseEntity.ok(service.getUserAccounts(id));
	}
	
	@GetMapping("/{id}/account/withBalance")
	@PreAuthorize("hasAnyAuthority('" + Roles.ADMIN + "', '" + Roles.USER + "')")
	public ResponseEntity<DTOAccountUserStatusResponse> getAccountByUserIdWithBalance(@PathVariable long id) throws NotFoundException, AccountWithoutMoneyException {
		return ResponseEntity.ok(service.getAccountByUserIdWithBalance(id));
	}

	@GetMapping("")
	@PreAuthorize("hasAnyAuthority('" + Roles.ADMIN + "', '" + Roles.USER + "')")
	public ResponseEntity<List<DTOUserResponse>> getUsers() {
		return ResponseEntity.ok(service.findAll());
	}

	@PostMapping("/register")
	public ResponseEntity<DTOUserResponse> saveUser(@RequestBody @Valid DTOUserRequest request) throws UserWithEmailAlreadyExistsException, InvalidRolesRequestException {
		return ResponseEntity.ok(service.save(request));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize( "hasAnyAuthority(\"" + Roles.ADMIN + "\" )" )
	public ResponseEntity<String> deleteUser(@PathVariable long id) throws NotFoundException {
		service.delete(id);
		return ResponseEntity.ok("The user with id " + id + " has been succesfully deleted."); 
	}
	
	@PutMapping("/{id}/status")
	@PreAuthorize( "hasAnyAuthority(\"" + Roles.ADMIN + "\" )" )
    public ResponseEntity<DTOUserResponse> updateUserStatus(@PathVariable long id, @RequestBody @Valid DTOUserStatusRequest request) throws NotFoundException {
        DTOUserResponse updatedUser = service.updateStatus(id, request);
        return ResponseEntity.ok(updatedUser);
    }
	
	@PostMapping("/{id}/addAccount/{accountId}")
	@PreAuthorize("hasAnyAuthority('" + Roles.ADMIN + "', '" + Roles.USER + "')")
    public ResponseEntity<String> addAccountToUser(@PathVariable Long id, @PathVariable Long accountId) throws NotFoundException, DisabledUserException {
        service.addAccountToUser(id, accountId);
        return ResponseEntity.ok("Added account " + accountId + " to user " +id);
    }
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('" + Roles.ADMIN + "', '" + Roles.USER + "')")
	public ResponseEntity<DTOUserResponse> getUserById(@PathVariable long id) throws NotFoundException {
			return ResponseEntity.ok(service.getUserById(id));
	}

	@GetMapping("/byCreatedAt")
	@PreAuthorize( "hasAnyAuthority(\"" + Roles.ADMIN + "\" )" )
	public ResponseEntity<List<DTOUserResponse>> getUsersBySimpleOrdering() {
		return ResponseEntity.ok(service.getUsersBySimpleOrdering());
	}

}
