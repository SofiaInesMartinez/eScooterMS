package tpe.maintenanceMS.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import jakarta.validation.Valid;
import tpe.maintenanceMS.dto.DTOMaintenanceRequest;
import tpe.maintenanceMS.dto.DTOMaintenanceResponse;
import tpe.maintenanceMS.dto.DTOScooterResponse;
import tpe.maintenanceMS.dto.DTOScooterStatusRequest;
import tpe.maintenanceMS.dto.ScooterByTimeDTO;
import tpe.maintenanceMS.dto.ScooterByTimePauseDTO;
import tpe.maintenanceMS.model.Maintenance;
import tpe.maintenanceMS.repository.MaintenanceRepository;

@Service("maintenanceService")
public class MaintenanceService {
	
	@Autowired
	private MaintenanceRepository repository; 
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Transactional
	public List<ScooterByTimePauseDTO> getScootersReportByTimeWithPauses() throws WebClientResponseException {
		List<ScooterByTimePauseDTO> scooters = webClientBuilder.build()
				.get()
				.uri("http://localhost:8002/scooter/reportByTimeWithPauses")
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<ScooterByTimePauseDTO>>(){})
				.block();
		
		return scooters;
	}
	
	@Transactional
	public List<ScooterByTimeDTO> getScootersReportByTotalTime() throws WebClientResponseException {
		List<ScooterByTimeDTO> scooters = webClientBuilder.build()
				.get()
				.uri("http://localhost:8002/scooter/reportByTotalTime")
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<ScooterByTimeDTO>>(){})
				.block();
		
		return scooters;
	}
	
	@Transactional
	public DTOMaintenanceResponse finishMaintenance(long id) throws Exception {
		Maintenance maintenance = repository.getMaintenanceById(id);
		if (maintenance != null && maintenance.getFinishDate() == null) {
			webClientBuilder.build()
				.put()
				.uri("http://localhost:8002/scooter/" + maintenance.getIdScooter() + "/status")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(BodyInserters.fromValue(new DTOScooterStatusRequest("available")))
				.retrieve()
				.bodyToMono(DTOScooterResponse.class)
				.block();
			
			maintenance.setFinishDate(new Date(System.currentTimeMillis()));
			return new DTOMaintenanceResponse(repository.save(maintenance));
		} else {
			throw new Exception("maintenance not found or already finished");
		}
	}
	
	@Transactional
	public DTOScooterResponse updateScooterStatus(long id, DTOScooterStatusRequest request) throws WebClientResponseException {
		DTOScooterResponse scooter = webClientBuilder.build()
			.put()
			.uri("http://localhost:8002/scooter/" + id + "/status")
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body(BodyInserters.fromValue(request))
			.retrieve()
			.bodyToMono(DTOScooterResponse.class)
			.block();
		return scooter;
	}
	
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
			webClientBuilder.build()
				.put()
				.uri("http://localhost:8002/scooter/" + request.getIdScooter() + "/status")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(BodyInserters.fromValue(new DTOScooterStatusRequest("in maintenance")))
				.retrieve()
				.bodyToMono(DTOScooterResponse.class)
				.block();
			
			Maintenance maintenance = new Maintenance(request.getId(), request.getDescription(),request.getStartDate(),request.getFinishDate(),request.getIdScooter());
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
