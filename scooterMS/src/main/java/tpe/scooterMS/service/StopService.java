package tpe.scooterMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import tpe.scooterMS.DTO.DTOStopRequest;
import tpe.scooterMS.DTO.DTOStopResponse;
import tpe.scooterMS.exception.NotFoundException;
import tpe.scooterMS.model.Stop;
import tpe.scooterMS.repository.StopRepository;


@Service("stopService")
public class StopService {
	
	@Autowired
	private StopRepository repository;
	
	@Transactional ( readOnly = true )
	public List<DTOStopResponse> findAll() {
		return repository.findAll().stream().map( DTOStopResponse::new ).toList();
	}
	
	@Transactional
	public DTOStopResponse save(@Valid DTOStopRequest request) {
		Stop stop = new Stop(request.getId(), request.getLatitude(), request.getLongitude());
		stop = repository.save(stop);
		return new DTOStopResponse(stop);
	}
	
	@Transactional ( readOnly = true )
	public DTOStopResponse getStopById(long id) throws NotFoundException {
		if (repository.existsById(id)) {
			return new DTOStopResponse(repository.getStopById(id));
		} else {
			throw new NotFoundException("Stop", id);
		}
	}
	
	@Transactional ( readOnly = true )
	public List<DTOStopResponse> getStopsBySimpleOrdering() {
		return repository.getStopsBySimpleOrdering().stream().map( DTOStopResponse::new ).toList();
	}
	
	@Transactional
	public void deleteStop(long id) throws NotFoundException {
		if (repository.existsById(id)) {
			repository.deleteStopById(id);
		} else {
			throw new NotFoundException("Stop", id);
		}
	}
}
