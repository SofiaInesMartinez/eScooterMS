package tpe.scooterMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tpe.scooterMS.model.Trip;

@Repository("tripRepository")
public interface TripRepository extends JpaRepository<Trip, Integer> {

	@Query("SELECT t"
			+ " FROM Trip t"
			+ " WHERE t.idUser = :idUser"
			+ " AND t.endDate IS NULL")
	public Trip getActiveTripByIdUser(@Param("idUser") long idUser);

	@Query("SELECT SUM(t.tripAmount)"
			+ " FROM Trip t"
			+ " WHERE YEAR(t.startDate) = :year"
			+ " AND MONTH(t.startDate) BETWEEN :month1 AND :month2")
	public Float getInvoicedAmountByYearAndMonthRange(@Param("year") int year, @Param("month1") int month1, @Param("month2") int month2);
}
