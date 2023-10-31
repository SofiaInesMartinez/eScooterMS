package tpe.userMS.DTO;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class DTOReduceBalanceRequest {
	@NotNull(message = "money shouldn't be null")
	@PositiveOrZero(message = "money should be positive or zero")
	private int money;
	
	public DTOReduceBalanceRequest() {
		super();
	}
	
	public DTOReduceBalanceRequest(int money) {
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOReduceBalanceRequest other = (DTOReduceBalanceRequest) obj;
		return money == other.money;
	}
}
