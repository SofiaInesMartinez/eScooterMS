package tpe.userMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import tpe.userMS.DTO.DTOAccountResponse;
import tpe.userMS.DTO.DTOUserRequest;
import tpe.userMS.DTO.DTOUserResponse;
import tpe.userMS.DTO.DTOUserStatusRequest;
import tpe.userMS.model.Account;
import tpe.userMS.model.User;
import tpe.userMS.repository.AccountRepository;
import tpe.userMS.repository.UserRepository;

@Service("userService")
public class UserService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private AccountRepository accountRepository;
	
	@Transactional(readOnly = true)
	public List<DTOAccountResponse> getUserAccounts(long id) throws Exception {
		Optional<User> optional = repository.findById(id);
		if (optional.isPresent()) {
			User user = optional.get();
			return repository.getUserAccounts(user).stream().map( DTOAccountResponse::new ).toList();
		} else {
			throw new Exception("User with id " + id + " not found");
		}
	}
	
	@Transactional(readOnly = true)
	public DTOAccountResponse getAccountByUserIdWithBalance(long id) {
		return repository.getAccountByUserIdWithBalance(id)
				.map( DTOAccountResponse::new )
				.orElseThrow(() -> new RuntimeException("Accounts associated to user with id " + id + " don't exist or they do not have balance"));
	}

	@Transactional(readOnly = true)
	public List<DTOUserResponse> findAll() throws Exception {
		try {
			return repository.findAll().stream().map(DTOUserResponse::new).toList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Transactional
	public DTOUserResponse save(@Valid DTOUserRequest request) throws Exception {
		try {
			User user = new User(request.getId(), request.getPhone(), request.getEmail(), request.getName(),
					request.getSurname(), request.getUsername(), request.getRole());
			user = repository.save(user);
			return new DTOUserResponse(user);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public DTOUserResponse getUserById(long id) throws Exception {
		try {
			return repository.getUserById(id).map(DTOUserResponse::new).get();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public List<DTOUserResponse> getUsersBySimpleOrdering() throws Exception {
		try {
			return repository.getUsersBySimpleOrdering().stream().map(DTOUserResponse::new).toList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Transactional
	public void delete(long id) throws Exception {
		try {
			repository.deleteUserById(id);
		} catch (NumberFormatException e) {
			throw new Exception("Invalid ID format");
		}
	}

	@Transactional
	public DTOUserResponse updateStatus(long id, DTOUserStatusRequest request) throws Exception {
		try {
			repository.updateUser(request.getStatus(), id);
			return repository.getUserById(id).map(DTOUserResponse::new).get();
		} catch (Exception e) {
			throw new Exception("Failed to update user status: " + e.getMessage());
		}
	}

	@Transactional
	public void addAccountToUser(Long id, Long accountId) throws Exception {
		try {
			User user = repository.findById(id)
					.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
			Account account = accountRepository.findById(accountId)
					.orElseThrow(() -> new RuntimeException("Account not found with id: " + accountId));
			user.getAccounts().add(account);
			repository.save(user);
			
		} catch (Exception e) {
			throw new Exception("Failed to update user account: " + e.getMessage());
		}
	}

}
