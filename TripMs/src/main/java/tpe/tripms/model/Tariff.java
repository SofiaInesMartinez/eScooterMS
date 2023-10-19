package tpe.tripms.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import tpe.tripms.dto.TariffRequestDTO;

@Data
@Entity
public class Tariff {
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
