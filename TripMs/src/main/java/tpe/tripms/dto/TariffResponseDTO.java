package tpe.tripms.dto;

import java.util.Date;

import lombok.Data;
import tpe.tripms.model.Tariff;

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
