package tpe.userMS.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Account {
	@Id
	private long id;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "accountId")
	private List<UserAccount> users;
	//private Wallet wallet;
	@Column(nullable=false)
	private int moneyBalance;
	@Column(nullable=false)
	private Date createdAt;
	
	
		
	public Account(long id, int moneyBalance, Date createdAt) {
		super();
		this.id = id;
		this.users = new ArrayList<>();
		this.moneyBalance = moneyBalance;
		this.createdAt = createdAt;
	}

/*
	public Account(long id, List<User> users) {
		super();
		this.id = id;
		this.users = users;
		this.createdAt = new Date(System.currentTimeMillis());
		this.moneyBalance = 0;
	}
	
*/
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public List<UserAccount> getUsers() {
		return users;
	}
	public void setUsers(List<UserAccount> users) {
		this.users = users;
	}

	public int getMoneyBalance() {
		return moneyBalance;
	}
	public void setMoneyBalance(int moneyBalance) {
		this.moneyBalance = moneyBalance;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", moneyBalance=" + moneyBalance + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return id == other.id;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}

