package tpe.administrationMS.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DTOAccountRequest {
	@NotNull(message = "id shouldn't be null")
	@Positive(message = "id shouldn't be negative or zero")
	private long id;
	@NotNull(message = "moneyBalance shouldn't be null")
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
