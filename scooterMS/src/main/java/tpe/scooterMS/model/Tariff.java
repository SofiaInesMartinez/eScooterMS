package tpe.scooterMS.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import tpe.scooterMS.DTO.TariffRequestDTO;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
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
	
	
}
