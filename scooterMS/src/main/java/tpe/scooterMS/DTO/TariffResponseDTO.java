package tpe.scooterMS.DTO;

import java.util.Date;

import lombok.Data;
import tpe.scooterMS.model.Tariff;

@Data
public class TariffResponseDTO {
	private int idTariff;
	private float normal;
	private float extra;
	private Date startDate;
	
	public TariffResponseDTO(Tariff tariff) {
		this.idTariff = tariff.getIdTariff();
		this.normal = tariff.getNormal();
		this.extra = tariff.getExtra();
		this.startDate = tariff.getStartDate();
	}
}
