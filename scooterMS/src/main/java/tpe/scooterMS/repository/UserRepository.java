package tpe.scooterMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tpe.scooterMS.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

}
