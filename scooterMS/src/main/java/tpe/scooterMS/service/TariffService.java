package tpe.scooterMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tpe.scooterMS.DTO.TariffRequestDTO;
import tpe.scooterMS.DTO.TariffResponseDTO;
import tpe.scooterMS.exception.NotFoundException;
import tpe.scooterMS.model.Tariff;
import tpe.scooterMS.repository.TariffRepository;

@Service("tariffService")
public class TariffService {
	
	@Autowired
	private TariffRepository repository;
	
	@Transactional( readOnly = true )
	public List<TariffResponseDTO> findAll() {
		return repository.findAll().stream().map( TariffResponseDTO::new ).toList();
	}
	
	@Transactional( readOnly = true )
	public TariffResponseDTO findById(int id) throws NotFoundException {
		return repository.findById(id)
				.map( TariffResponseDTO::new )
				.orElseThrow(() -> new NotFoundException("Tariff", id));
	}
	
	@Transactional
	public TariffResponseDTO saveTariff(TariffRequestDTO request) {
		Tariff tariff = repository.save(new Tariff(request));
		return new TariffResponseDTO(tariff);
	}
	
	@Transactional
	public TariffResponseDTO deleteTariff(int id) throws NotFoundException {
		Optional<Tariff> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
			return new TariffResponseDTO(optional.get());
		} else {
			throw new NotFoundException("Tariff", id);
		}
	}
}
