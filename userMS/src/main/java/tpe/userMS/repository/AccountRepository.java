package tpe.userMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tpe.userMS.model.Account;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query("SELECT a FROM Account a WHERE a.id = :id")
	public Optional<Account> getAccountById(long id);


	@Query("SELECT a FROM Account a ORDER BY a.id")
	public List<Account> getAccountsBySimpleOrdering();

	@Modifying
	@Query("DELETE FROM Account a WHERE a.id = :id")
	void deleteAccountById(@Param("id") long id);

	@Modifying
	@Query("UPDATE Account a SET a.money_balance = :money WHERE u.id = :id")
	void updateAccount(@Param("status") int money, @Param("id") long id);
}
