package tpe.userMS.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import tpe.userMS.DTO.DTOAccountRequest;
import tpe.userMS.DTO.DTOAccountResponse;
import tpe.userMS.model.Account;
import tpe.userMS.model.User;
import tpe.userMS.repository.AccountRepository;
import tpe.userMS.repository.UserRepository;

@Service("accountService")
public class AccountService {
	
	@Autowired
	private AccountRepository repository;
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public DTOAccountResponse getAccountByUserIdWithBalance(long userId) throws Exception, NoSuchElementException {
		try {
			return repository.getAccountByUserIdWithBalance(userId)
					.map( DTOAccountResponse::new )
					.orElseThrow(() -> new Exception("Accounts associated to user with id" + userId + " don't exist or they do not have balance"));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public List<DTOAccountResponse> findAll() throws Exception {
		try {
			return repository.findAll().stream().map(DTOAccountResponse::new).toList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Transactional
	public DTOAccountResponse save(@Valid DTOAccountRequest request) throws Exception {
		try {
			Account account = new Account(request.getId(), request.getMoneyBalance());
			account = repository.save(account);
			return new DTOAccountResponse(account);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	
		@Transactional(readOnly = true)
		public DTOAccountResponse getAccountById(long id) throws Exception {
		    try {
		        return repository.getAccountById(id)
		                .map(DTOAccountResponse::new)
		                .orElseThrow(() -> new Exception("Account not found for id: " + id));
		    } catch (Exception e) {
		        throw new Exception(e.getMessage());
		    }
		}

		
	@Transactional(readOnly = true)
	public List<DTOAccountResponse> getUsersBySimpleOrdering() throws Exception {
		try {
			return repository.getAccountsBySimpleOrdering().stream().map(DTOAccountResponse::new).toList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Transactional
	public void delete(long id) throws Exception {
		try {
			repository.deleteAccountById(id);
		} catch (NumberFormatException e) {
	        throw new Exception("Invalid ID format");
		}
	}


    @Transactional
    public void updateMoneyBalance(long id, int money) throws Exception {
        try {
            repository.updateAccount(money, id);
        } catch (Exception e) {
            throw new Exception("Failed to update account money balance: " + e.getMessage());
        }
    }

	@Transactional
	public void addUserToAccount(Long id, Long userId) throws Exception {
		try {
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new RuntimeException("User not found with userId: " + userId));
			Account account = repository.findById(id)
					.orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
//			account.getUsers().add(user);
//			repository.save(account); No funciona porque user es el duenio de la relacion
			
			user.getAccounts().add(account);
			userRepository.save(user);
			
		} catch (Exception e) {
			throw new Exception("Failed to update account user: " + e.getMessage());
		}
	}

}
