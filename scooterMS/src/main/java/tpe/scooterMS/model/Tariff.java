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
	private int idTariff;
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
}
