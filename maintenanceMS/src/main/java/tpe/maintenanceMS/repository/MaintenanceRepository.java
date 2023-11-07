package tpe.maintenanceMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import tpe.maintenanceMS.model.Maintenance;

@Repository("maintenanceRepository")
public interface MaintenanceRepository extends MongoRepository<Maintenance, Long> {

	Maintenance getMaintenanceById(long id);

	List<Maintenance> findAll(Sort sort);
	
	Optional<Maintenance> findByFinishDateIsNullAndIdScooter(long idScooter);
}
