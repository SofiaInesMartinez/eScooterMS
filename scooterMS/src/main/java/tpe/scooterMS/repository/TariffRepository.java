package tpe.scooterMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tpe.scooterMS.model.Tariff;

@Repository("tariffRepository")
public interface TariffRepository extends JpaRepository<Tariff, Integer> {

}
