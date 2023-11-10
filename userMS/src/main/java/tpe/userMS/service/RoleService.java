package tpe.userMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tpe.userMS.DTO.DTORoleRequest;
import tpe.userMS.DTO.DTORoleResponse;
import tpe.userMS.exception.NotFoundException;
import tpe.userMS.exception.RoleWithNameAlreadyExistsException;
import tpe.userMS.model.Role;
import tpe.userMS.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	public DTORoleResponse saveRole(DTORoleRequest request) throws RoleWithNameAlreadyExistsException {
		Optional<Role> optional = repository.findByName(request.getName());
		if (optional.isPresent()) {
			throw new RoleWithNameAlreadyExistsException(request.getName());
		}
		
		return new DTORoleResponse(repository.save(new Role(request.getId(), request.getName())));
	}
	
	public DTORoleResponse findById(long id) throws NotFoundException {
		Optional<Role> optional = repository.findById(id);
		if (optional.isPresent()) {
			Role role = optional.get();
			return new DTORoleResponse(role);
		} else {
			throw new NotFoundException("Role", id);
		}
	}
	
	public List<DTORoleResponse> findAll() {
		return repository.findAll().stream().map( DTORoleResponse::new ).toList();
	}
}
