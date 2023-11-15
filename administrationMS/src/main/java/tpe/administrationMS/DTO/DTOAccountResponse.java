package tpe.administrationMS.DTO;

import tpe.administrationMS.model.Account;

public class DTOAccountResponse {
	private long id;
	private int moneyBalance;
	
	
	public DTOAccountResponse(Account a) {
		super();
		this.id = a.getId();
		this.moneyBalance = a.getMoneyBalance();
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
