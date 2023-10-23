package tpe.tripms.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tpe.tripms.dto.ScooterByKmsDTO;
import tpe.tripms.dto.ScooterByTimeDTO;
import tpe.tripms.dto.TripRequestDTO;
import tpe.tripms.dto.TripResponseDTO;
import tpe.tripms.model.Trip;
import tpe.tripms.repository.TripRepository;
import utils.TimerThread;
import utils.TripTimer;

@Service("tripService")
public class TripService {

	@Autowired
	private TripRepository repository;
	
	private HashMap<Trip, Timer> timers;
	
	public TripService() {
		this.timers = new HashMap<>();
	}
	
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
	@Transactional
	public TripResponseDTO endPause(int id) throws Exception {
		Optional<Trip> optional = repository.findById(id);
		if (optional.isPresent()) {
			Trip trip = optional.get();
			
//			if (trip.getTimerId() != -1) {
//				for (Thread thread : Thread.getAllStackTraces().keySet()) {
//					if (thread.getId() == trip.getTimerId()) {
//						thread.interrupt();
//						trip.setTimerId(-1);
//						break;
//					}
//				}
//				return new TripResponseDTO(repository.save(trip));
			Timer timer = timers.remove(trip);
			if (timer != null) {
				timer.cancel();
				return new TripResponseDTO(repository.save(trip));
			} else {
				throw new Exception();
			}
		} else {
			throw new Exception();
		}
	}
	
	/*
	 * Crea un timer y ejecuta el run de la clase TripTimer cada segundo
	 * No suma bien los segundo y minutos !!!
	 */
	@Transactional
	public TripResponseDTO startPause(int id) throws Exception {
		Optional<Trip> optional = repository.findById(id);
		if (optional.isPresent()) {
			Trip trip = optional.get();
//			TimerThread timerThread = new TimerThread(trip);
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TripTimer(trip), 0, 1000);
			timers.put(trip, timer);
//			timerThread.start();
//			trip.setTimerId(timerThread.getId());
			
			
			return new TripResponseDTO(repository.save(trip));
		} else {
			throw new Exception();
		}
	}
	
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
	public TripResponseDTO saveTrip(TripRequestDTO request) throws InterruptedException {
		Trip trip = repository.save(new Trip(request));
		return new TripResponseDTO(trip);
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
