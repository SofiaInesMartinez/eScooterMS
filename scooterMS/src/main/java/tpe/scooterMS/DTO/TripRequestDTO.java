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
	
	public TripRequestDTO(int idUser,int idScooter, int idOriginStop) {
		super();
		this.idUser = idUser;
		this.idScooter = idScooter;
		this.idOriginStop = idOriginStop;
	}

	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdScooter() {
		return idScooter;
	}
	public void setIdScooter(int idScooter) {
		this.idScooter = idScooter;
	}
	public int getIdOriginStop() {
		return idOriginStop;
	}
	public void setIdOriginStop(int idOriginStop) {
		this.idOriginStop = idOriginStop;
	}
	
	
}
//public class TripRequestDTO {
//	private User user;
//	private Scooter scooter;
//	private Stop destinationStop;
//	private Stop originStop;
//}


