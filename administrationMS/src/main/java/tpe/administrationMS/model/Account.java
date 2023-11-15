package tpe.administrationMS.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@SuppressWarnings("serial")
@Entity
@JsonIgnoreProperties(value = {"users"})
public class Account implements Serializable{
	@Id
	private long id;
	
	@ManyToMany(mappedBy = "accounts")
	private List<User> users;
	//private Wallet wallet;
	@Column(nullable=false)
	private int moneyBalance;
	@Column(nullable=false)
	private Date createdAt;
	
	

	public Account() {
		super();
	}
		
	public Account(long id, int moneyBalance) {
		super();
		this.id = id;
		this.users = new ArrayList<>();
		this.moneyBalance = moneyBalance;
		this.createdAt = new Date(System.currentTimeMillis());
	}


	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
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

	


}

