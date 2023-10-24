package tpe.scooterMS.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tpe.scooterMS.model.Scooter;

@Repository("scooterRepository")
public interface ScooterRepository extends JpaRepository<Scooter, Integer>{
	@Query("SELECT s FROM Scooter s WHERE s.id = :id")
	public Scooter getScooterById(@Param("id")long id);

	@Query("SELECT s FROM Scooter s ORDER BY s.id DESC")
	public List<Scooter> getScootersBySimpleOrdering();
	
	@Query("DELETE FROM Scooter s WHERE s.id = :id")
    void deleteScooterById(@Param("id") long id);
	
	@Query("SELECT s FROM Scooter s ORDER BY s.kilometers DESC")
	public List<Scooter> getScootersReportByKm();
}
