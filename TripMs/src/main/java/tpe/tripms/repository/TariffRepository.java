package tpe.tripms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tpe.tripms.model.Tariff;

@Repository("tariffRepository")
public interface TariffRepository extends JpaRepository<Tariff, Integer> {

}
