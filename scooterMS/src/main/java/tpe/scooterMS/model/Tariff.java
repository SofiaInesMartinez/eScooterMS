package tpe.scooterMS.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import tpe.scooterMS.DTO.TariffRequestDTO;

@SuppressWarnings("serial")
@Entity
public class Tariff implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private float normal;
	@Column(nullable = false)
	private float extra;
	@Column(nullable = false)
	private Date startDate;
	
	public Tariff() {
		super();
	}
	
	public Tariff(TariffRequestDTO request) {
		this.normal = request.getNormal();
		this.extra = request.getExtra();
		this.startDate = request.getStartDate();
	}

	public int getIdTariff() {
		return id;
	}

	public void setIdTariff(int idTariff) {
		this.id = idTariff;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(extra, id, normal, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tariff other = (Tariff) obj;
		return Float.floatToIntBits(extra) == Float.floatToIntBits(other.extra) && id == other.id
				&& Float.floatToIntBits(normal) == Float.floatToIntBits(other.normal)
				&& Objects.equals(startDate, other.startDate);
	}
}
