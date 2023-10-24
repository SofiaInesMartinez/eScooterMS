package tpe.tripms.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tpe.tripms.model.Maintenance;

@Repository("maintenanceRepository")
public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer>{
	@Query("SELECT m FROM Maintenance m WHERE m.id = :id")
	public Maintenance getMaintenanceById(@Param("id")long id);

	@Query("SELECT m FROM Maintenance m ORDER BY m.id DESC")
	public List<Maintenance> getMaintenancesBySimpleOrdering();
}
