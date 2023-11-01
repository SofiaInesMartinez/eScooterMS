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
import tpe.userMS.DTO.DTOAccountRequest;
import tpe.userMS.DTO.DTOAccountResponse;
import tpe.userMS.DTO.DTOAccountUserStatusResponse;
import tpe.userMS.DTO.DTOReduceBalanceRequest;
import tpe.userMS.exception.AccountWithoutMoneyException;
import tpe.userMS.exception.DisabledUserException;
import tpe.userMS.exception.NotFoundException;
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
	public ResponseEntity<DTOAccountUserStatusResponse> getAccountByUserIdWithBalance(@PathVariable long userId) throws AccountWithoutMoneyException {
		return ResponseEntity.ok(service.getAccountByUserIdWithBalance(userId));
	}

	@GetMapping("")
	public ResponseEntity<List<DTOAccountResponse>> getAccounts() {
		return ResponseEntity.ok(service.findAll());
	}

	@PostMapping("")
	public ResponseEntity<DTOAccountResponse> saveAccount(@RequestBody @Valid DTOAccountRequest request) {
			return ResponseEntity.ok(service.save(request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable long id) throws NotFoundException {
			service.delete(id);
			return ResponseEntity.ok("The account with id " + id + " has been succesfully deleted."); 
	}
	
	@PutMapping("/{id}/reduceMoneyBalance")
    public ResponseEntity<String> reduceAccountMoneyBalance(@PathVariable long id, @RequestBody @Valid DTOReduceBalanceRequest request) throws NotFoundException {
        service.reduceMoneyBalance(id, request);
        return ResponseEntity.ok("Account with ID " + id + " has reduced its balance an amount of " + request.getMoney());
    }
	
	@PutMapping("/{id}/moneyBalance/{moneyBalance}")
    public ResponseEntity<String> updateAccountMoneyBalance(@PathVariable long id, @PathVariable int moneyBalance) throws NotFoundException {
        service.updateMoneyBalance(id, moneyBalance);
        return ResponseEntity.ok("Account with ID " + id + " has been updated money balance to " + moneyBalance);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<DTOAccountResponse> getAccountById(@PathVariable long id) throws NotFoundException {
		return ResponseEntity.ok(service.getAccountById(id));
	}

	@GetMapping("/byId")
	public ResponseEntity<List<DTOAccountResponse>> getAccountsBySimpleOrdering() {
		return ResponseEntity.ok(service.getAccountsBySimpleOrdering());
	}
	
	@PostMapping("/{id}/addUser/{userId}")
    public ResponseEntity<String> addUserToAccount(@PathVariable Long id, @PathVariable Long userId) throws NotFoundException, DisabledUserException {
        service.addUserToAccount(id, userId);
        return ResponseEntity.ok("Added user " + userId + " to account " +id);
    }

}
