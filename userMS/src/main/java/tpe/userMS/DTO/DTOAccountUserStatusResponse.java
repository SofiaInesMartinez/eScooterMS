package tpe.userMS.DTO;

import tpe.userMS.model.Account;
import tpe.userMS.model.User;

public class DTOAccountUserStatusResponse {
	private long id;
	private int moneyBalance;
	private long idUser;
	private int userStatus;
	
	public DTOAccountUserStatusResponse(Account a, User user) {
		super();
		this.id = a.getId();
		this.moneyBalance = a.getMoneyBalance();
		this.idUser = user.getId();
		this.userStatus = user.getStatus();
	}
	public DTOAccountUserStatusResponse(long id, int moneyBalance, long idUser, int userStatus) {
		super();
		this.id = id;
		this.moneyBalance = moneyBalance;
		this.idUser = idUser;
		this.userStatus = userStatus;
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
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public int getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
}
