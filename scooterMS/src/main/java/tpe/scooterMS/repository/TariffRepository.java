package tpe.scooterMS.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tpe.scooterMS.model.Tariff;

@Repository("tariffRepository")
public interface TariffRepository extends JpaRepository<Tariff, Integer> {

	@Query("SELECT t"
			+ " FROM Tariff t"
			+ " WHERE t.startDate <= :endDate"
			+ " ORDER BY t.startDate DESC"
			+ " LIMIT 1")
	public Tariff getTariffByDate(@Param("endDate") Date endDate);
}