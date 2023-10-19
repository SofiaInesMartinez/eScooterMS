package tpe.tripms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tpe.tripms.dto.TripRequestDTO;
import tpe.tripms.dto.TripResponseDTO;
import tpe.tripms.model.Trip;
import tpe.tripms.repository.TripRepository;

@Service("tripService")
public class TripService {

	@Autowired
	private TripRepository repository;
	
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
	
	@Transactional
	public List<TripResponseDTO> findAll() {
		return repository.findAll().stream().map( TripResponseDTO::new ).toList();
	}
	
	@Transactional
	public TripResponseDTO findByid(int id) throws Exception {
		return repository.findById(id)
				.map( TripResponseDTO::new )
				.orElseThrow(() -> new Exception());
	}
	
	@Transactional
	public TripResponseDTO saveTrip(TripRequestDTO request) {
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
