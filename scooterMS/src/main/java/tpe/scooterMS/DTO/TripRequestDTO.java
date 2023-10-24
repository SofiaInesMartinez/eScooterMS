package tpe.scooterMS.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TripRequestDTO {
	@NotNull(message = "idUser shouldn't be null")
	private long idUser;
	@NotNull(message = "idScooter shouldn't be null")
	private int idScooter;
	@NotNull(message = "idOriginStop shouldn't be null")
	private int idOriginStop;
}