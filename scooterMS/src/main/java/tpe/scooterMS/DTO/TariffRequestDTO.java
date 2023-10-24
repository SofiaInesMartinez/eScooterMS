package tpe.scooterMS.DTO;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TariffRequestDTO {
	@NotNull(message = "normal shouldn't be null")
	private float normal;
	@NotNull(message = "extra shouldn't be null")
	private float extra;
	@NotNull(message = "startDate shouldn't be null")
	private Date startDate;
}
