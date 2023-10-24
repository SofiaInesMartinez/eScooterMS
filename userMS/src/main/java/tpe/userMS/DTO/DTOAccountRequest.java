package tpe.userMS.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DTOAccountRequest {
	@NotNull(message = "id shouldn't be null")
	private long id;
	@NotNull(message = "moneyBalance shouldn't be null")
	@NotEmpty(message = "moneyBalance shouldn't be empty")
	private int moneyBalance;
	
	
	public DTOAccountRequest(long id, int moneyBalance) {
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

	
}
