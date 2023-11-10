package tpe.userMS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tpe.userMS.model.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	@Query("SELECT r"
			+ " FROM Role r"
			+ " WHERE e.name = :name") 
	public Optional<Role> findByName(@Param("name") String name);
}
