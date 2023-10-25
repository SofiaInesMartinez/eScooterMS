package tpe.scooterMS.DTO;

import java.util.Objects;

public class DTOInvoicedAmountResponse {
	private int year;
	private int month1;
	private int month2;
	private float invoicedAmount;
	
	public DTOInvoicedAmountResponse() {
		super();
	}
	
	public DTOInvoicedAmountResponse(int year, int month1, int month2, float invoicedAmount) {
		this.year = year;
		this.month1 = month1;
		this.month2 = month2;
		this.invoicedAmount = invoicedAmount;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth1() {
		return month1;
	}

	public void setMonth1(int month1) {
		this.month1 = month1;
	}

	public int getMonth2() {
		return month2;
	}

	public void setMonth2(int month2) {
		this.month2 = month2;
	}

	public float getInvoicedAmount() {
		return invoicedAmount;
	}

	public void setInvoicedAmount(float invoicedAmount) {
		this.invoicedAmount = invoicedAmount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(invoicedAmount, month1, month2, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOInvoicedAmountResponse other = (DTOInvoicedAmountResponse) obj;
		return Float.floatToIntBits(invoicedAmount) == Float.floatToIntBits(other.invoicedAmount)
				&& month1 == other.month1 && month2 == other.month2 && year == other.year;
	}
}
