package tpe.scooterMS.model;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class Account implements Serializable {
	private long id;
	private int moneyBalance;
	
	public Account() {
		super();
	}
	
	public Account(long id, int moneyBalance) {
		this.id = id;
		this.moneyBalance = moneyBalance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMoneyBalance() {
		return moneyBalance;
	}

	public void setMoneyBalance(int moneyBalance) {
		this.moneyBalance = moneyBalance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, moneyBalance);
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
		return id == other.id && moneyBalance == other.moneyBalance;
	}
}
