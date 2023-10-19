package tpe.tripms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tpe.tripms.model.Trip;

@Repository("tripRepository")
public interface TripRepository extends JpaRepository<Trip, Integer> {

}
