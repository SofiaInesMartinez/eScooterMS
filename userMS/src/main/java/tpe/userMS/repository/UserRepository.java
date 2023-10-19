package tpe.userMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tpe.userMS.model.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("SELECT u FROM User u WHERE u.id = :id")
	public Optional<User> getUserById(long number);

	@Query("SELECT u FROM User u ORDER BY u.createdAt DESC")
	public List<User> getUsersBySimpleOrdering();
	


	
}
