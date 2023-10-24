package tpe.userMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import tpe.userMS.DTO.DTOUserRequest;
import tpe.userMS.DTO.DTOUserResponse;
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
	public void updateStatus(long id, String status) throws Exception {
		try {
			repository.updateUser(status, id);
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
