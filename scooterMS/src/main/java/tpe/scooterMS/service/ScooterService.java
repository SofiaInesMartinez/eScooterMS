package tpe.scooterMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import tpe.scooterMS.DTO.DTOScooterRequest;
import tpe.scooterMS.DTO.DTOScooterResponse;
import tpe.scooterMS.model.Scooter;
import tpe.scooterMS.repository.ScooterRepository;

@Service("scooterService")
public class ScooterService {
	
	@Autowired
	private ScooterRepository repository;
	
	@Transactional ( readOnly = true )
	public List<DTOScooterResponse> findAll() throws Exception {
		try {
			return repository.findAll().stream().map( DTOScooterResponse::new ).toList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Transactional
	public DTOScooterResponse save(@Valid DTOScooterRequest request) throws Exception {
		try {
			Scooter scooter = new Scooter(request.getId(),request.getLastMaintenanceDate());
			scooter = repository.save(scooter);
			return new DTOScooterResponse(scooter);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Transactional ( readOnly = true )
	public DTOScooterResponse getScooterById(long id) throws Exception {
		try {
			return new DTOScooterResponse(repository.getScooterById(id));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Transactional ( readOnly = true )
	public List<DTOScooterResponse> getScootersBySimpleOrdering() throws Exception {
		try {
			return repository.getScootersBySimpleOrdering().stream().map( DTOScooterResponse::new ).toList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Transactional
	public void deleteScooter(long id) throws Exception {
		try {
	          repository.deleteScooterById(id);
	    } catch (Exception e) {
	        throw new Exception(e.getMessage());
	    }
	}

}
