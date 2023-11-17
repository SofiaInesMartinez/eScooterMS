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

import tpe.scooterMS.DTO.DTOAccountUserStatusResponse;
import tpe.scooterMS.DTO.DTOInvoicedAmountResponse;
import tpe.scooterMS.DTO.DTOReduceBalanceRequest;
import tpe.scooterMS.DTO.TripRequestDTO;
import tpe.scooterMS.DTO.TripResponseDTO;
import tpe.scooterMS.exception.AccountWithoutMoneyException;
import tpe.scooterMS.exception.DisabledUserException;
import tpe.scooterMS.exception.NotFoundException;
import tpe.scooterMS.exception.ScooterNotLocatedAtStopException;
import tpe.scooterMS.exception.ScooterUnavailableException;
import tpe.scooterMS.exception.TripAlreadyEndedException;
import tpe.scooterMS.exception.TripInPauseException;
import tpe.scooterMS.exception.TripNotPausedException;
import tpe.scooterMS.exception.TripReachedPauseTimeLimitException;
import tpe.scooterMS.exception.UserOnTripException;
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
	public TripResponseDTO endPause(int id) throws NotFoundException, TripNotPausedException, TripAlreadyEndedException {
		Optional<Trip> optional = repository.findById(id);
		if (optional.isPresent()) {
			Trip trip = optional.get();
			
			if (trip.getEndDate() != null) {
				throw new TripAlreadyEndedException(id);
			}
			if (!trip.isPaused()) {
				throw new TripNotPausedException(id);
			}
			
			trip.setPaused(false);
			return new TripResponseDTO(repository.save(trip));
		} else {
			throw new NotFoundException("Trip", id);
		}
	}
	
	public TripResponseDTO startPause(int id) throws NotFoundException, TripInPauseException, TripAlreadyEndedException, TripReachedPauseTimeLimitException {
		Optional<Trip> optional = repository.findById(id);
		if (optional.isPresent()) {
			Trip trip = optional.get();
			
			if (trip.getEndDate() != null) {
				throw new TripAlreadyEndedException(id);
			}
			if (trip.isPaused()) {
				throw new TripInPauseException(id);
			}
			if (trip.getPauseTime() == 0) {
				throw new TripReachedPauseTimeLimitException(id);
			}
			
			trip.setPaused(true);
			return new TripResponseDTO(repository.save(trip));
		} else {
			throw new NotFoundException("Trip", id);
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
	
	@Transactional
	public TripResponseDTO endTrip(int id) throws NotFoundException, TripAlreadyEndedException, ScooterNotLocatedAtStopException {
		Optional<Trip> optional = repository.findById(id);
		if (optional.isPresent()) {
			Trip trip = optional.get();
			if (trip.getEndDate() == null) {
				Optional<Stop> stopOptional = stopRepository.getStopByCoordinates(trip.getScooter().getLatitude(), trip.getScooter().getLongitude());
				if (stopOptional.isPresent()) {
					trip.setDestinationStop(stopOptional.get());
				} else {
					throw new ScooterNotLocatedAtStopException(id);
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
					.uri("http://localhost:8005/account/" + trip.getIdAccount() + "/reduceMoneyBalance")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(BodyInserters.fromValue(new DTOReduceBalanceRequest((int) amount)))
					.retrieve()
					.bodyToMono(String.class)
					.block();
				
				return new TripResponseDTO(repository.save(trip));
			} else {
				throw new TripAlreadyEndedException(id);
			}
		} else {
			throw new NotFoundException("Trip", id);
		}
	}
	
	@Transactional( readOnly = true )
	public List<TripResponseDTO> findAll() {
		return repository.findAll().stream().map( TripResponseDTO::new ).toList();
	}
	
	@Transactional( readOnly = true )
	public TripResponseDTO findByid(int id) throws NotFoundException {
		return repository.findById(id)
				.map( TripResponseDTO::new )
				.orElseThrow(() -> new NotFoundException("Trip", id));
	}
	
	@Transactional
	public TripResponseDTO saveTrip(TripRequestDTO request) throws NotFoundException, AccountWithoutMoneyException, ScooterUnavailableException, UserOnTripException, DisabledUserException {
		DTOAccountUserStatusResponse account = null;
		try {
			account = webClientBuilder.build()
					.get()
					.uri("http://localhost:8005/account/user/" + request.getIdUser() + "/withBalance")
					.retrieve()
					.bodyToMono(DTOAccountUserStatusResponse.class)
					.block();
		} catch (Exception e) {
			throw new AccountWithoutMoneyException(request.getIdUser());
		}
		
		if (account.getUserStatus() == 0) {
			throw new DisabledUserException(request.getIdUser());
		}
			
		Optional<Scooter> scooterOptional = scooterRepository.findById(request.getIdScooter());
		Optional<Stop> stopOptional = stopRepository.findById(request.getIdOriginStop());
		
		if (!scooterOptional.isPresent()) {
			throw new NotFoundException("Scooter", request.getIdScooter());
		}
		if (!stopOptional.isPresent()) {
			throw new NotFoundException("Stop", request.getIdOriginStop());
		}
		if (account.getMoneyBalance() == 0 || account == null) {
			throw new AccountWithoutMoneyException(request.getIdUser());
		}
		
		Scooter scooter = scooterOptional.get();
		Stop originStop = stopOptional.get();
		
		if (scooter.getStatus().equals("available")) {
			Trip activeTrip = repository.getActiveTripByIdUser(request.getIdUser());
			if (activeTrip == null) {
				Trip trip = repository.save(new Trip(request.getIdUser(), account.getId(), scooter, originStop));
				scooterRepository.updateScooterStatus(scooter.getId(), "in use");
				return new TripResponseDTO(trip);
			} else {
				throw new UserOnTripException(request.getIdUser());
			}
		} else {
			throw new ScooterUnavailableException(request.getIdScooter());
		}
	}
	
	@Transactional
	public TripResponseDTO deleteTrip(int id) throws NotFoundException {
		Optional<Trip> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
			return new TripResponseDTO(optional.get());
		} else {
			throw new NotFoundException("Trip", id);
		}
			
	}
}
