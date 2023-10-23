package tpe.userMS.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@SuppressWarnings("serial")
@Entity
public class UserAccount implements Serializable {
	@Id
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("id")
	@JoinColumn(name="id")
	private User userId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("id")
	@JoinColumn(name="id")
	private Account accountId;


	public UserAccount(User user, Account account) {
		super();
		this.userId = user;
		this.accountId = account;
	}


	

	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public User getUserId() {
		return userId;
	}




	public void setUserId(User userId) {
		this.userId = userId;
	}




	public Account getAccountId() {
		return accountId;
	}




	public void setAccountId(Account accountId) {
		this.accountId = accountId;
	}




	public UserAccount() {
		super();
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		return Objects.equals(accountId, other.accountId) && Objects.equals(userId, other.userId);
	}
	



}
