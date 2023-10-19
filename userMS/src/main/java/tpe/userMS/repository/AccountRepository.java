package tpe.userMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tpe.userMS.model.Account;


@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query("SELECT a FROM Account a WHERE a.id = :id")
	public Account getAcountById(long id);
}
