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
import tpe.userMS.DTO.DTOAccountRequest;
import tpe.userMS.service.AccountService;

@RestController
@RequestMapping("account")
public class AccountController {
	
	@Autowired(required = true)
	private final AccountService service;

	public AccountController(AccountService service) {
		this.service = service;
	}
	
	@GetMapping("/user/{userId}/withBalance")
	public ResponseEntity<?> getAccountByUserIdWithBalance(@PathVariable long userId) {
		try {
			return ResponseEntity.ok(service.getAccountByUserIdWithBalance(userId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
		}
	}

	@GetMapping("")
	public ResponseEntity<?> getAccounts() {
		try {
			return ResponseEntity.ok(service.findAll());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error: Internal server error");
		}
	}

	@PostMapping("")
	public ResponseEntity<?> saveAccount(@RequestBody @Valid DTOAccountRequest request) {
		try {
			return ResponseEntity.ok(service.save(request));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: Failed to save");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable long id) {
		try {
			service.delete(id);
			return ResponseEntity.ok("The account with id " + id + " has been succesfully deleted."); 
		} catch (Exception e) {	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Not found");
		}
	}
	
	@PutMapping("/{id}/moneyBalance/{moneyBalance}")
    public ResponseEntity<?> updateAccountMoneyBalance(@PathVariable long id, @PathVariable int moneyBalance) {
        try {
            service.updateMoneyBalance(id, moneyBalance);
            return ResponseEntity.ok("Account with ID " + id + " has been updated money balance to " + moneyBalance);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable long id) {
		try {
			return ResponseEntity.ok(service.getAccountById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Not found");
		}
	}

	@GetMapping("/byId")
	public ResponseEntity<?> getAccountsBySimpleOrdering() {
		try {
			return ResponseEntity.ok(service.getUsersBySimpleOrdering());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error: Internal server error");
		}
	}
	
	@PostMapping("/{id}/addUser/{userId}")
    public ResponseEntity<?> addUserToAccount(@PathVariable Long id, @PathVariable Long userId) {
		try {
        service.addUserToAccount(id, userId);
        return ResponseEntity.ok("Added user " + userId + " to account " +id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Not found");
		}
    }

}
