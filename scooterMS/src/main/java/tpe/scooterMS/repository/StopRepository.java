package tpe.scooterMS.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tpe.scooterMS.model.Stop;

@Repository("stopRepository")
public interface StopRepository extends JpaRepository<Stop, Long>{
	@Query("SELECT s FROM Stop s WHERE s.id = :id")
	public Stop getStopById(@Param("id")long id);

	@Query("SELECT s FROM Stop s ORDER BY s.id DESC")
	public List<Stop> getStopsBySimpleOrdering();
	
	@Modifying
	@Query("DELETE FROM Stop s WHERE s.id = :id")
    void deleteStopById(@Param("id") long id);
}
