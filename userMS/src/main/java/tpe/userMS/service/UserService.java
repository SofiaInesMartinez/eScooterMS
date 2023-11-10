package tpe.userMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

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
	@Autowired
	private WebClient restClient;
	
	@Transactional(readOnly = true)
	public List<DTOScooterResponse> getNearbyScooters(double latitude, double longitude) {
		return restClient
				.method(HttpMethod.GET)
				.uri("http://localhost:8002/scooter/latitude/" + latitude + "/longitude/" + longitude)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<DTOScooterResponse>>(){})
				.block();
	}
	
	@Transactional(readOnly = true)
	public List<DTOAccountResponse> getUserAccounts(long id) throws NotFoundException {
		Optional<User> optional = repository.findById(id);
		if (optional.isPresent()) {
			User user = optional.get();
			return repository.getUserAccounts(user).stream().map( DTOAccountResponse::new ).toList();
		} else {
			throw new NotFoundException("User", id);
		}
	}
	
	@Transactional(readOnly = true)
	public DTOAccountUserStatusResponse getAccountByUserIdWithBalance(long id) throws NotFoundException, AccountWithoutMoneyException {
		if (repository.existsById(id)) {
//			return repository.getAccountByUserIdWithBalance(id)
//					.map( DTOAccountResponse::new )
//					.orElseThrow(() -> new AccountWithoutMoneyException(id));
			Optional<DTOAccountUserStatusResponse> optional = repository.getAccountByUserIdWithBalance(id);
			if (optional.isPresent()) {
				return optional.get();
			} else {
				throw new AccountWithoutMoneyException(id);
			}
		} else {
			throw new NotFoundException("User", id);
		}
	}

	@Transactional(readOnly = true)
	public List<DTOUserResponse> findAll() {
		return repository.findAll().stream().map(DTOUserResponse::new).toList();
	}

	@Transactional
	public DTOUserResponse save(@Valid DTOUserRequest request) {
		User user = new User(request.getId(), request.getPhone(), request.getEmail(), request.getPassword(), request.getName(),
				request.getSurname(), request.getUsername(), request.getRole());
		user = repository.save(user);
		return new DTOUserResponse(user);
	}

	@Transactional(readOnly = true)
	public DTOUserResponse getUserById(long id) throws NotFoundException {
		if (repository.existsById(id)) {
			return repository.getUserById(id).map(DTOUserResponse::new).get();
		} else {
			throw new NotFoundException("User", id);
		}
	}

	@Transactional(readOnly = true)
	public List<DTOUserResponse> getUsersBySimpleOrdering() {
		return repository.getUsersBySimpleOrdering().stream().map(DTOUserResponse::new).toList();
	}

	@Transactional
	public void delete(long id) throws NotFoundException {
		if (repository.existsById(id)) {
			repository.deleteUserById(id);
		} else {
			throw new NotFoundException("User", id);
		}
	}

	@Transactional
	public DTOUserResponse updateStatus(long id, DTOUserStatusRequest request) throws NotFoundException {
		if (repository.existsById(id)) {
			repository.updateUser(request.getStatus(), id);
			return repository.getUserById(id).map(DTOUserResponse::new).get();
		} else {
			throw new NotFoundException("User", id);
		}
	}

	@Transactional
	public void addAccountToUser(Long id, Long accountId) throws NotFoundException, DisabledUserException {
		User user = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("User", id));
		
		if (user.getStatus() == 0) {
			throw new DisabledUserException(id);
		}
		
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new NotFoundException("Account", accountId));
		user.getAccounts().add(account);
		repository.save(user);
	}

}
