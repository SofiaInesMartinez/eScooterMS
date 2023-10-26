package tpe.scooterMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import tpe.scooterMS.DTO.DTOScooterRequest;
import tpe.scooterMS.DTO.DTOScooterResponse;
import tpe.scooterMS.DTO.DTOScooterStatusRequest;
import tpe.scooterMS.DTO.ScooterByKmsDTO;
import tpe.scooterMS.DTO.ScooterByTimeDTO;
import tpe.scooterMS.DTO.ScooterByTimePauseDTO;
import tpe.scooterMS.model.Scooter;
import tpe.scooterMS.repository.ScooterRepository;

@Service("scooterService")
public class ScooterService {
	
	@Autowired
	private ScooterRepository repository;
	
	@Transactional
	public DTOScooterResponse updateScooterStatus(long id, DTOScooterStatusRequest request) throws Exception {
		Scooter scooter = repository.getScooterById(id);
		if (scooter != null) {
			scooter.setStatus(request.getStatus());
			return new DTOScooterResponse(repository.save(scooter));
		} else {
			throw new Exception("scooter not found");
		}
	}
	
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
			Scooter scooter = new Scooter(request.getId(),request.getLastMaintenanceDate(),request.getKms());
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
	
	@Transactional ( readOnly = true )
	public List<ScooterByKmsDTO> getScootersReportByKm() throws Exception {
		try {
			return repository.getScootersReportByKm().stream().map( ScooterByKmsDTO::new ).toList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Transactional ( readOnly = true )
	public List<ScooterByTimePauseDTO> getScootersReportByTotalTime() throws Exception {
		try {
			return repository.getScootersReportByTotalTime().stream().map( ScooterByTimePauseDTO::new ).toList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Transactional ( readOnly = true )
	public List<ScooterByTimeDTO> getScootersReportByTimeWithPauses() throws Exception {
		try {
			return repository.getScootersReportByTimeWithPauses().stream().map( ScooterByTimeDTO::new ).toList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Transactional ( readOnly = true )
	public List<DTOScooterResponse> getScootersByMinimumNumberOfTrips(int number, int year) throws Exception {
		try {
			return repository.getScootersByMinimumNumberOfTrips(number,year).stream().map( DTOScooterResponse::new ).toList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
