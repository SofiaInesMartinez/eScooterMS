package tpe.scooterMS.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tpe.scooterMS.DTO.DTOCoordinatesRequest;
import tpe.scooterMS.DTO.DTOScooterRequest;
import tpe.scooterMS.DTO.DTOScooterResponse;
import tpe.scooterMS.DTO.DTOScooterStatusRequest;
import tpe.scooterMS.DTO.ScooterByKmsDTO;
import tpe.scooterMS.DTO.ScooterByTimeDTO;
import tpe.scooterMS.DTO.ScooterByTimePauseDTO;
import tpe.scooterMS.exception.NotFoundException;
import tpe.scooterMS.model.Scooter;
import tpe.scooterMS.repository.ScooterRepository;

@Service("scooterService")
public class ScooterService {
	
	@Autowired
	private ScooterRepository repository;
	
	@Transactional
	public DTOScooterResponse updateScooterCoordinates(long id, DTOCoordinatesRequest request) throws NotFoundException {
		Optional<Scooter> optional = repository.findById(id);
		if (optional.isPresent()) {
			Scooter scooter = optional.get();
			scooter.setLatitude(request.getLatitude());
			scooter.setLongitude(request.getLongitude());
			
			return new DTOScooterResponse(repository.save(scooter));
		} else {
			throw new NotFoundException("Scooter", id);
		}
	}
	
	@Transactional
	public List<DTOScooterResponse> getNearbyScooters(double latitude, double longitude) {
		return repository.getNearbyScooters(latitude, longitude).stream().map( DTOScooterResponse::new ).toList();
	}
	
	@Transactional
	public DTOScooterResponse updateScooterStatus(long id, DTOScooterStatusRequest request) throws NotFoundException {
		Scooter scooter = repository.getScooterById(id);
		if (scooter != null) {
			scooter.setStatus(request.getStatus());
			return new DTOScooterResponse(repository.save(scooter));
		} else {
			throw new NotFoundException("Scooter", id);
		}
	}
	
	@Transactional ( readOnly = true )
	public List<DTOScooterResponse> findAll() {
		return repository.findAll().stream().map( DTOScooterResponse::new ).toList();
	}
	
	@Transactional
	public DTOScooterResponse save(DTOScooterRequest request) {
		Scooter scooter = new Scooter(request.getId(),request.getLastMaintenanceDate(),request.getKilometers(), request.getLatitude(), request.getLongitude());		
		scooter = repository.save(scooter);
		return new DTOScooterResponse(scooter);
	}
	
	@Transactional ( readOnly = true )
	public DTOScooterResponse getScooterById(long id) throws NotFoundException {
		if (repository.existsById(id)) {
			return new DTOScooterResponse(repository.getScooterById(id));
		} else {
			throw new NotFoundException("Scooter", id);
		}
	}
	
	@Transactional ( readOnly = true )
	public List<DTOScooterResponse> getScootersBySimpleOrdering()  {
		return repository.getScootersBySimpleOrdering().stream().map( DTOScooterResponse::new ).toList();
	}
	
	@Transactional
	public void deleteScooter(long id) throws NotFoundException {
		if (repository.existsById(id)) {
			repository.deleteScooterById(id);
		} else {
			throw new NotFoundException("Scooter", id);
		}
	}
	
	@Transactional ( readOnly = true )
	public List<ScooterByKmsDTO> getScootersReportByKm() {
		return repository.getScootersReportByKm().stream().map( ScooterByKmsDTO::new ).toList();
	}
	
	@Transactional ( readOnly = true )
	public List<ScooterByTimePauseDTO> getScootersReportByTotalTime() {
		return repository.getScootersReportByTotalTime().stream().map( ScooterByTimePauseDTO::new ).toList();
	}
	
	@Transactional ( readOnly = true )
	public List<ScooterByTimeDTO> getScootersReportByTimeWithPauses() {
		return repository.getScootersReportByTimeWithPauses().stream().map( ScooterByTimeDTO::new ).toList();
	}
	
	@Transactional ( readOnly = true )
	public List<DTOScooterResponse> getScootersByMinimumNumberOfTrips(int number, int year) {
		return repository.getScootersByMinimumNumberOfTrips(number,year).stream().map( DTOScooterResponse::new ).toList();
	}
	
	@Transactional ( readOnly = true )
	public  Map<String, Long> getScootersByStatus() {
		return repository.getScootersByStatus();
	}

}
