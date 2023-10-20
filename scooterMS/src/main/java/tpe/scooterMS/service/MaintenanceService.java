package tpe.scooterMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import tpe.scooterMS.DTO.DTOMaintenanceRequest;
import tpe.scooterMS.DTO.DTOMaintenanceResponse;
import tpe.scooterMS.model.Maintenance;
import tpe.scooterMS.repository.MaintenanceRepository;



@Service("maintenanceService")
public class MaintenanceService {
	
	@Autowired
	private MaintenanceRepository repository;
	
	@Transactional ( readOnly = true )
	public List<DTOMaintenanceResponse> findAll() throws Exception {
		try {
			return repository.findAll().stream().map( DTOMaintenanceResponse::new ).toList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Transactional
	public DTOMaintenanceResponse save(@Valid DTOMaintenanceRequest request) throws Exception {
		try {
			Maintenance maintenance = new Maintenance(request.getId(), request.getDescription(),request.getStartDate(),request.getFinishDate(),request.getScooter());
			maintenance = repository.save(maintenance);
			return new DTOMaintenanceResponse(maintenance);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Transactional ( readOnly = true )
	public DTOMaintenanceResponse getMaintenanceById(long id) throws Exception {
		try {
			return new DTOMaintenanceResponse(repository.getMaintenanceById(id));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Transactional ( readOnly = true )
	public List<DTOMaintenanceResponse> getMaintenancesBySimpleOrdering() throws Exception {
		try {
			return repository.getMaintenancesBySimpleOrdering().stream().map( DTOMaintenanceResponse::new ).toList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
