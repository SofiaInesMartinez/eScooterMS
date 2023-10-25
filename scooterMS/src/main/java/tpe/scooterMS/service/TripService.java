package tpe.scooterMS.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import tpe.scooterMS.DTO.DTOInvoicedAmountResponse;
import tpe.scooterMS.DTO.TripRequestDTO;
import tpe.scooterMS.DTO.TripResponseDTO;
import tpe.scooterMS.model.Scooter;
import tpe.scooterMS.model.Stop;
import tpe.scooterMS.model.Trip;
import tpe.scooterMS.model.User;
import tpe.scooterMS.repository.ScooterRepository;
import tpe.scooterMS.repository.StopRepository;
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
	
	
//	@Transactional( readOnly = true )
//	public List<ScooterByKmsDTO> getKmsReport() {
//		return repository.getScootersKms();
//	}
//	
//	@Transactional( readOnly = true )
//	public List<ScooterByTimeDTO> getTimeReport() {
//		return repository.getScootersTripTime();
//	}
	
//	@Transactional
//	public List<ScooterByTimeDTO> getTimeWithoutPauseReport() {
//		return repository.getScootersTripTimeWithoutPause();
//	}
	
	/*
	 * Eliminar el mapping de un determinado trip. Si el trip no tenia timer tira error
	 * Por alguna razón siempre tira error !!!
	 */
//	@Transactional //COMENTADO POR AHORA
//	public TripResponseDTO endPause(int id) throws Exception {
//		Optional<Trip> optional = repository.findById(id);
//		if (optional.isPresent()) {
//			Trip trip = optional.get();
			
//			if (trip.getTimerId() != -1) {
//				for (Thread thread : Thread.getAllStackTraces().keySet()) {
//					if (thread.getId() == trip.getTimerId()) {
//						thread.interrupt();
//						trip.setTimerId(-1);
//						break;
//					}
//				}
//				return new TripResponseDTO(repository.save(trip));
//			System.out.println(timers);
//			TripTimer tripTimer = timers.remove(trip.getIdTrip());
//			System.out.println(timers);
//			if (tripTimer != null) {
//				tripTimer.cancel();
//				return new TripResponseDTO(repository.save(trip));
//			} else {
//				throw new Exception();
//			}
//		} else {
//			throw new Exception();
//		}
//	}
	
	/*
	 * Crea un timer y ejecuta el run de la clase TripTimer cada segundo
	 * No suma bien los segundo y minutos !!!
	 */
//	@Transactional //COMENTADO POR AHORA
//	public TripResponseDTO startPause(int id) throws Exception {
//		Optional<Trip> optional = repository.findById(id);
//		if (optional.isPresent()) {
//			Trip trip = optional.get();
			
//			if (trip.getTimerId() == -1) {
//				TimerThread timerThread = new TimerThread(trip);
//				timerThread.start();
//				trip.setTimerId(timerThread.getId());
//				
//				return new TripResponseDTO(repository.save(trip));
//			} else {
//				throw new Exception();
//			}
			
//			Timer timer = new Timer();
//			TripTimer tripTimer = new TripTimer(trip);
//			timer.scheduleAtFixedRate(tripTimer, 0, 1000);
//			timers.put(trip.getIdTrip(), tripTimer);
//			System.out.println(timers);
//			
//			return new TripResponseDTO(repository.save(trip));
//		} else {
//			throw new Exception();
//		}
//	}
	
	@Transactional
	@Async
	@Scheduled(fixedRate = 60000) // un minuto
	public void updatePause(int id) throws Exception {
		Optional<Trip> optional = repository.findById(id);
		if (optional.isPresent()) {
			Trip trip = optional.get();
			long pauseTime = trip.getPauseTime();
			if (pauseTime > 0) {
				trip.setPauseTime(pauseTime - 1);
				repository.save(trip);
			}
		} else {
			throw new Exception();
		}
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
			trip.setEndDate(new Date(System.currentTimeMillis()));
			return new TripResponseDTO(repository.save(trip));
		} else {
			throw new Exception();
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
		User user = webClientBuilder.build() // status >= 400: = WebClientResponseException
				.get()
				.uri("http://localhost:8003/user/" + request.getIdUser())
				.retrieve()
				.bodyToMono(User.class)
				.block();
		
		Optional<Scooter> scooterOptional = scooterRepository.findById(request.getIdScooter());
		Optional<Stop> stopOptional = stopRepository.findById(request.getIdOriginStop());
		
		if (scooterOptional.isPresent() && stopOptional.isPresent() && user != null) {
			Scooter scooter = scooterOptional.get();
			Stop originStop = stopOptional.get();
			Trip trip = repository.save(new Trip(user.getId(), scooter, originStop));
			return new TripResponseDTO(trip);
		} else {
			throw new Exception();
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
