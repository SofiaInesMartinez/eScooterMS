package tpe.scooterMS.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TripRequestDTO {
	@NotNull(message = "idUser shouldn't be null")
	private int idUser;
	@NotNull(message = "idScooter shouldn't be null")
	private int idScooter;
	@NotNull(message = "idOriginStop shouldn't be null")
	private int idOriginStop;
}
//public class TripRequestDTO {
//	private User user;
//	private Scooter scooter;
//	private Stop destinationStop;
//	private Stop originStop;
//}