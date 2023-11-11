package tpe.administrationMS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tpe.administrationMS.model.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT u"
			+ " FROM User u"
			+ " WHERE u.email = :email")
	Optional<User> findUserByEmailIgnoreCase(String email);
}
