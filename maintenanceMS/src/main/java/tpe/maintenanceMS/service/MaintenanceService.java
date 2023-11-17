package tpe.maintenanceMS.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import tpe.maintenanceMS.dto.DTOMaintenanceRequest;
import tpe.maintenanceMS.dto.DTOMaintenanceResponse;
import tpe.maintenanceMS.dto.DTOScooterResponse;
import tpe.maintenanceMS.dto.DTOScooterStatusRequest;
import tpe.maintenanceMS.dto.ScooterByTimeDTO;
import tpe.maintenanceMS.dto.ScooterByTimePauseDTO;
import tpe.maintenanceMS.exception.MaintenanceAlreadyFinishedException;
import tpe.maintenanceMS.exception.NotFoundException;
import tpe.maintenanceMS.exception.ScooterAlreadyInMaintenanceException;
import tpe.maintenanceMS.exception.ServiceCommunicationException;
import tpe.maintenanceMS.model.Maintenance;
import tpe.maintenanceMS.repository.MaintenanceRepository;

@Service("maintenanceService")
public class MaintenanceService {
	
	@Autowired
	private MaintenanceRepository repository; 
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Transactional
	public List<ScooterByTimePauseDTO> getScootersReportByTimeWithPauses(String token) throws ServiceCommunicationException {
		try {
			List<ScooterByTimePauseDTO> scooters = webClientBuilder.build()
					.get()
					.uri("http://localhost:8001/scooter/reportByTimeWithPauses")
					.header("Authorization", token)
					.retrieve()
					.bodyToMono(new ParameterizedTypeReference<List<ScooterByTimePauseDTO>>(){})
					.block();
			
			return scooters;
		} catch (Exception e) {
			throw new ServiceCommunicationException();
		}
	}
	
	@Transactional
	public List<ScooterByTimeDTO> getScootersReportByTotalTime(String token) throws ServiceCommunicationException {
		try {
			List<ScooterByTimeDTO> scooters = webClientBuilder.build()
					.get()
					.uri("http://localhost:8001/scooter/reportByTotalTime")
					.header("Authorization", token)
					.retrieve()
					.bodyToMono(new ParameterizedTypeReference<List<ScooterByTimeDTO>>(){})
					.block();
			
			return scooters;
		} catch (Exception e) {
			throw new ServiceCommunicationException();
		}
	}
	
	@Transactional
	public DTOMaintenanceResponse finishMaintenance(String token, long id) throws NotFoundException, MaintenanceAlreadyFinishedException, ServiceCommunicationException {
		Maintenance maintenance = repository.getMaintenanceById(id);
		if (maintenance == null) {
			throw new NotFoundException("Maintenance", id);
		}
		if (maintenance.getFinishDate() != null) {
			throw new MaintenanceAlreadyFinishedException(id);
		}
		
		long idScooter = maintenance.getIdScooter();
		try {
			webClientBuilder.build()
				.put()
				.uri("http://localhost:8001/scooter/" + idScooter + "/status")
				.header("Authorization", token)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(BodyInserters.fromValue(new DTOScooterStatusRequest("available")))
				.retrieve()
				.bodyToMono(DTOScooterResponse.class)
				.block();
		
			maintenance.setFinishDate(new Date(System.currentTimeMillis()));
			return new DTOMaintenanceResponse(repository.save(maintenance));
		} catch (Exception e) {
			throw new ServiceCommunicationException();
		}
	}
	
	@Transactional
	public DTOScooterResponse updateScooterStatus(String token, long id, DTOScooterStatusRequest request) throws NotFoundException {
		try {
			DTOScooterResponse scooter = webClientBuilder.build()
				.put()
				.uri("http://localhost:8001/scooter/" + id + "/status")
				.header("Authorization", token)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(BodyInserters.fromValue(request))
				.retrieve()
				.bodyToMono(DTOScooterResponse.class)
				.block();
			
			return scooter;
		} catch (Exception e) {
			throw new NotFoundException("Scooter", id);
		}
	}
	
	@Transactional ( readOnly = true )
	public List<DTOMaintenanceResponse> findAll() {
		return repository.findAll().stream().map( DTOMaintenanceResponse::new ).toList();
	}
	
	@Transactional
	public DTOMaintenanceResponse save(String token, DTOMaintenanceRequest request) throws NotFoundException, ScooterAlreadyInMaintenanceException {
		Optional<Maintenance> activeMaintenance = repository.findByFinishDateIsNullAndIdScooter(request.getIdScooter());
		if (activeMaintenance.isPresent()) {
			throw new ScooterAlreadyInMaintenanceException(request.getIdScooter());
		}
		
		try {
			long idScooter = request.getIdScooter();
			webClientBuilder.build()
				.put()
				.uri("http://localhost:8001/scooter/" + idScooter + "/status")
				.header("Authorization", token)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(BodyInserters.fromValue(new DTOScooterStatusRequest("maintenance")))
				.retrieve()
				.bodyToMono(DTOScooterResponse.class)
				.block();
			Maintenance maintenance = new Maintenance(request.getId(), request.getDescription(),request.getStartDate(),request.getFinishDate(),request.getIdScooter());
			maintenance = repository.save(maintenance);
			return new DTOMaintenanceResponse(maintenance);
		} catch (Exception e) {
			throw new NotFoundException("Scooter", request.getIdScooter());
		}

	}
	
	@Transactional ( readOnly = true )
	public DTOMaintenanceResponse getMaintenanceById(long id) throws NotFoundException {
		Optional<Maintenance> optional = repository.findById(id);
		if (optional.isPresent()) {
			return new DTOMaintenanceResponse(optional.get());
		} else {
			throw new NotFoundException("Maintenance", id);
		}
	}
	
	@Transactional ( readOnly = true )
	public List<DTOMaintenanceResponse> getMaintenancesBySimpleOrdering() {
		return repository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream().map( DTOMaintenanceResponse::new ).toList();
	}
}
