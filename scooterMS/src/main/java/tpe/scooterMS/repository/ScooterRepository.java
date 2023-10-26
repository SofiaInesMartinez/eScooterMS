package tpe.scooterMS.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tpe.scooterMS.model.Scooter;

@Repository("scooterRepository")
public interface ScooterRepository extends JpaRepository<Scooter, Long>{
	@Query("SELECT s FROM Scooter s WHERE s.id = :id")
	public Scooter getScooterById(@Param("id")long id);

	@Query("SELECT s FROM Scooter s ORDER BY s.id DESC")
	public List<Scooter> getScootersBySimpleOrdering();
	
	@Modifying
	@Query("DELETE FROM Scooter s WHERE s.id = :id")
    void deleteScooterById(@Param("id") long id);
	
	@Query("SELECT s FROM Scooter s ORDER BY s.kilometers DESC")
	public List<Scooter> getScootersReportByKm();
	
	@Query("SELECT s FROM Scooter s ORDER BY s.totalTime DESC")
	public List<Scooter> getScootersReportByTotalTime();
	
	@Query("SELECT s FROM Scooter s ORDER BY (s.totalTime - s.timePause) DESC")
	public List<Scooter> getScootersReportByTimeWithPauses();
	
	@Query("SELECT s "
			+ "FROM Scooter s "
			+ "WHERE s.id IN ("
			+ "    SELECT t.scooter.id "
			+ "    FROM Trip t"
			+ "    WHERE YEAR(t.startDate) = :year"
			+ "    GROUP BY t.scooter.id"
			+ "    HAVING COUNT(*) > :number"
			+ ")")
	public List<Scooter> getScootersByMinimumNumberOfTrips(@Param("number") int number,@Param("year") int year);
}
