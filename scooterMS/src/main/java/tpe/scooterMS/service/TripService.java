package tpe.scooterMS.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import tpe.scooterMS.DTO.DTOInvoicedAmountResponse;
import tpe.scooterMS.DTO.DTOReduceBalanceRequest;
import tpe.scooterMS.DTO.TripRequestDTO;
import tpe.scooterMS.DTO.TripResponseDTO;
import tpe.scooterMS.model.Account;
import tpe.scooterMS.model.Scooter;
import tpe.scooterMS.model.Stop;
import tpe.scooterMS.model.Tariff;
import tpe.scooterMS.model.Trip;
import tpe.scooterMS.repository.ScooterRepository;
import tpe.scooterMS.repository.StopRepository;
import tpe.scooterMS.repository.TariffRepository;
import tpe.scooterMS.repository.TripRepository;

@Service("tripService")
@EnableAsync
public class TripService {
	
	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	private TripRepository repository;
	
	@Autowired
	private ScooterRepository scooterRepository;
	
	@Autowired
	private StopRepository stopRepository;
	
	@Autowired
	private TariffRepository tariffRepository;
	
	@Transactional
	public TripResponseDTO endPause(int id) throws Exception {
		Optional<Trip> optional = repository.findById(id);
		if (optional.isPresent()) {
			Trip trip = optional.get();
			
			if (!trip.isPaused()) {
				throw new Exception("Trip with id " + id + " is not paused");
			}
			if (trip.getEndDate() != null) {
				throw new Exception("Trip with id " + id + " already ended");
			}
			
			trip.setPaused(false);
			return new TripResponseDTO(repository.save(trip));
		} else {
			throw new Exception("Trip with id " + id + " does not exist");
		}
	}
	
	public TripResponseDTO startPause(int id) throws Exception {
		Optional<Trip> optional = repository.findById(id);
		if (optional.isPresent()) {
			Trip trip = optional.get();
			
			if (trip.isPaused()) {
				throw new Exception("Trip with id " + id + " is already in pause");
			}
			if (trip.getEndDate() != null) {
				throw new Exception("Trip with id " + id + " already ended");
			}
			if (trip.getPauseTime() == 0) {
				throw new Exception("Trip with id " + id + " reached pause limit");
			}
			
			trip.setPaused(true);
			return new TripResponseDTO(repository.save(trip));
		} else {
			throw new Exception("Trip with id " + id + " does not exist");
		}
	}
	
	@Transactional
	@Async
	@Scheduled(fixedRate = 60000) // un minuto
	public void updatePause() {
		repository.updatePause();
		repository.updateIsPausedOfTripsReachedLimit();
	}
	
	@Transactional
	public DTOInvoicedAmountResponse getInvoicedAmountByYearAndMonthRange(int year, int month1, int month2) {
		Float invoiceAmount = repository.getInvoicedAmountByYearAndMonthRange(year, month1, month2);
		if (invoiceAmount != null) {
			return new DTOInvoicedAmountResponse(year, month1, month2, invoiceAmount.floatValue());
		} else {
			return new DTOInvoicedAmountResponse(year, month1, month2, 0);
		}
	}
	
	// VERIFICAR QUE EL MONOPATIN ESTE UBICADO EN UNA PARADA, SINO ERROR
	@Transactional
	public TripResponseDTO endTrip(int id) throws Exception {
		Optional<Trip> optional = repository.findById(id);
		if (optional.isPresent()) {
			Trip trip = optional.get();
			if (trip.getEndDate() == null) {
				Optional<Stop> stopOptional = stopRepository.getStopByCoordinates(trip.getScooter().getLatitude(), trip.getScooter().getLongitude());
				if (stopOptional.isPresent()) {
					trip.setDestinationStop(stopOptional.get());
				} else {
					throw new Exception("The scooter is not located at a stop");
				}
				
				trip.setEndDate(new Date(System.currentTimeMillis()));
				
				long time = (trip.getEndDate().getTime() - trip.getStartDate().getTime()) / 60000; // minutos
				Tariff tariff = tariffRepository.getTariffByDate(trip.getEndDate());  
				float kms = 3 * time; // El 3 es un numero random, podria ser cualquiera. Es para calcular de alguna forma los kms
				float amount = tariff.getNormal() * time; // Por cada minuto le cobra la tarifa normal
				
				if (trip.getPauseTime() == 0) {
					amount += tariff.getExtra();
				}
				
				trip.setKms(kms);
				trip.setTripAmount(amount);
				trip.setPaused(false);
				
				long idScooter = trip.getScooter().getId();
				scooterRepository.updateScooterStatus(idScooter, "available");
				scooterRepository.incrementScooterKms(idScooter, kms);
				scooterRepository.incrementScooterTotalTime(idScooter, time);
				scooterRepository.incrementScooterTimePause(idScooter, trip.getInitialPauseTime() - trip.getPauseTime());
				
				webClientBuilder.build()
					.put()
					.uri("http://localhost:8003/account/" + trip.getIdAccount() + "/reduceMoneyBalance")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(BodyInserters.fromValue(new DTOReduceBalanceRequest((int) amount)))
					.retrieve()
					.bodyToMono(String.class)
					.block();
				
				return new TripResponseDTO(repository.save(trip));
			} else {
				throw new Exception("The trip already ended");
			}
		} else {
			throw new Exception("The trip with id " + id + "doesn't exist");
		}
	}
	
	@Transactional( readOnly = true )
	public List<TripResponseDTO> findAll() {
		return repository.findAll().stream().map( TripResponseDTO::new ).toList();
	}
	
	@Transactional( readOnly = true )
	public TripResponseDTO findByid(int id) throws Exception {
		return repository.findById(id)
				.map( TripResponseDTO::new )
				.orElseThrow(() -> new Exception());
	}
	
	@Transactional
	public TripResponseDTO saveTrip(TripRequestDTO request) throws Exception, WebClientResponseException {
		Account account = webClientBuilder.build()
				.get()
				.uri("http://localhost:8003/account/user/" + request.getIdUser() + "/withBalance")
				.retrieve()
				.bodyToMono(Account.class)
				.block();
		
		Optional<Scooter> scooterOptional = scooterRepository.findById(request.getIdScooter());
		Optional<Stop> stopOptional = stopRepository.findById(request.getIdOriginStop());
		
		if (scooterOptional.isPresent() && stopOptional.isPresent() && account.getMoneyBalance() > 0) {
			Scooter scooter = scooterOptional.get();
			Stop originStop = stopOptional.get();
			
			if (scooter.getStatus().equals("available")) {
				Trip activeTrip = repository.getActiveTripByIdUser(request.getIdUser());
				if (activeTrip == null) {
					Trip trip = repository.save(new Trip(request.getIdUser(), account.getId(), scooter, originStop));
					scooterRepository.updateScooterStatus(scooter.getId(), "in use");
					return new TripResponseDTO(trip);
				} else {
					throw new Exception("The specified user is on a trip");
				}
			} else {
				throw new Exception("The scooter is unavailable");
			}
		} else {
			throw new Exception("The scooter, the stop or the user doesn't exist or the user doesn't have money in any account");
		}
	}
	
	@Transactional
	public TripResponseDTO deleteTrip(int id) throws Exception {
		Optional<Trip> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
			return new TripResponseDTO(optional.get());
		} else {
			throw new Exception();
		}
			
	}
}
