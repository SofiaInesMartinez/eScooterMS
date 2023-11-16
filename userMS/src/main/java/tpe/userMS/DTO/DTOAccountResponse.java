package tpe.userMS.DTO;

import tpe.userMS.model.Account;

public class DTOAccountResponse {
	private long id;
	private int moneyBalance;
	
	
	public DTOAccountResponse(Account a) {
		super();
		this.id = a.getId();
		this.moneyBalance = a.getMoneyBalance();
	}
	
	
	public DTOAccountResponse(long id, int moneyBalance) {
		super();
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
	public String toString() {
		return "DTOAccountResponse [id=" + id + ", money=" + moneyBalance + "]";
	}
	
	
}
