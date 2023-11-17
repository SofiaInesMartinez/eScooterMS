package tpe.administrationMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tpe.administrationMS.DTO.DTOAccountUserStatusResponse;
import tpe.administrationMS.model.Account;
import tpe.administrationMS.model.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u"
			+ " FROM User u"
			+ " WHERE u.email = :email")
	Optional<User> findUserByEmailIgnoreCase(String email);
	
	@Query("SELECT CASE WHEN EXISTS("
				+ "SELECT 1"
				+ " FROM User u"
				+ " WHERE u.email = :email)"
			+ " THEN TRUE"
			+ " ELSE FALSE END")
	public boolean existsUserByEmail(@Param("email") String email);
	
	@Query("SELECT a"
			+ " FROM User u"
			+ " JOIN u.accounts a"
			+ " JOIN a.users u2"
			+ " WHERE u2 = :user")
	public List<Account> getUserAccounts(@Param("user") User user);
	
	@Query("SELECT new tpe.administrationMS.DTO.DTOAccountUserStatusResponse(a.id, a.moneyBalance, u.id, u.status)"
			+ " FROM User u"
			+ " JOIN u.accounts a"
			+ " WHERE u.id = :id"
			+ " AND a.moneyBalance > 0"
			+ " ORDER BY a.moneyBalance DESC"
			+ " LIMIT 1")
	public Optional<DTOAccountUserStatusResponse> getAccountByUserIdWithBalance(@Param("id") long id);

	@Query("SELECT u FROM User u WHERE u.id = :id")
	public Optional<User> getUserById(long id);

	@Query("SELECT u FROM User u ORDER BY u.surname")
	public List<User> getUsersBySimpleOrdering();
	
	@Modifying
    @Query("DELETE FROM User u WHERE u.id = :id")
    void deleteUserById(@Param("id") long id);

	@Modifying
    @Query("UPDATE User u SET u.status = :status WHERE u.id = :id")
    void updateUser(@Param("status") int status, @Param("id") long id);
}
