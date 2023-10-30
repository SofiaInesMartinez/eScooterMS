package tpe.scooterMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import tpe.scooterMS.DTO.DTOStopRequest;
import tpe.scooterMS.DTO.DTOStopResponse;
import tpe.scooterMS.model.Stop;
import tpe.scooterMS.repository.StopRepository;


@Service("stopService")
public class StopService {
	
	@Autowired
	private StopRepository repository;
	
	@Transactional ( readOnly = true )
	public List<DTOStopResponse> findAll() throws Exception {
		try {
			return repository.findAll().stream().map( DTOStopResponse::new ).toList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Transactional
	public DTOStopResponse save(@Valid DTOStopRequest request) throws Exception {
		try {
			Stop stop = new Stop(request.getId(), request.getLatitude(), request.getLongitude());
			stop = repository.save(stop);
			return new DTOStopResponse(stop);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Transactional ( readOnly = true )
	public DTOStopResponse getStopById(long id) throws Exception {
		try {
			return new DTOStopResponse(repository.getStopById(id));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Transactional ( readOnly = true )
	public List<DTOStopResponse> getStopsBySimpleOrdering() throws Exception {
		try {
			return repository.getStopsBySimpleOrdering().stream().map( DTOStopResponse::new ).toList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Transactional
	public void deleteStop(long id) throws Exception {
		try {
	          repository.deleteStopById(id);
	    } catch (Exception e) {
	        throw new Exception(e.getMessage());
	    }
	}
}
