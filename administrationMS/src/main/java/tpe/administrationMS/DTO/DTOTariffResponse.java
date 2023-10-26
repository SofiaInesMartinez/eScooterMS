package tpe.administrationMS.DTO;

import java.util.Date;
import java.util.Objects;


public class DTOTariffResponse {
	private int idTariff;
	private float normal;
	private float extra;
	private Date startDate;
	
	public DTOTariffResponse() {
		super();
	}
	
	public int getIdTariff() {
		return idTariff;
	}

	public void setIdTariff(int idTariff) {
		this.idTariff = idTariff;
	}

	public float getNormal() {
		return normal;
	}

	public void setNormal(float normal) {
		this.normal = normal;
	}

	public float getExtra() {
		return extra;
	}

	public void setExtra(float extra) {
		this.extra = extra;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(extra, idTariff, normal, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOTariffResponse other = (DTOTariffResponse) obj;
		return Float.floatToIntBits(extra) == Float.floatToIntBits(other.extra) && idTariff == other.idTariff
				&& Float.floatToIntBits(normal) == Float.floatToIntBits(other.normal)
				&& Objects.equals(startDate, other.startDate);
	}

}
