package tpe.scooterMS.DTO;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TripRequestDTO {
	@NotNull(message = "idUser shouldn't be null")
	@Positive(message = "idUser shouldn't be negative or zero")
	private long idUser;
	@NotNull(message = "idScooter shouldn't be null")
	@Positive(message = "idScooter shouldn't be negative or zero")
	private long idScooter;
	@NotNull(message = "idOriginStop shouldn't be null")
	@Positive(message = "idOriginStop shouldn't be negative or zero")
	private long idOriginStop;
	
	public TripRequestDTO(int idUser,int idScooter, int idOriginStop) {
		super();
		this.idUser = idUser;
		this.idScooter = idScooter;
		this.idOriginStop = idOriginStop;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdScooter() {
		return idScooter;
	}

	public void setIdScooter(long idScooter) {
		this.idScooter = idScooter;
	}

	public long getIdOriginStop() {
		return idOriginStop;
	}

	public void setIdOriginStop(long idOriginStop) {
		this.idOriginStop = idOriginStop;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idOriginStop, idScooter, idUser);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TripRequestDTO other = (TripRequestDTO) obj;
		return idOriginStop == other.idOriginStop && idScooter == other.idScooter && idUser == other.idUser;
	}
}