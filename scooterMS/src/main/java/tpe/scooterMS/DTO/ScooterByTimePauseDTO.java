package tpe.scooterMS.DTO;

import java.util.Objects;

import tpe.scooterMS.model.Scooter;

public class ScooterByTimePauseDTO {
	private long idScooter;
	private long time;
	
	public ScooterByTimePauseDTO(long id, long time) {
		super();
		this.idScooter = id;
		this.time = time;
	}
	
	public ScooterByTimePauseDTO(Scooter scooter) {
		super();
		this.idScooter = scooter.getId();
		this.time = scooter.getTimePause();
	}
	
	
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getId() {
		return idScooter;
	}
	public void setId(long id) {
		this.idScooter = id;
	}

	@Override
	public String toString() {
		return "ScooterByTimeDTO [idScooter=" + idScooter + ", time=" + time + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idScooter, time);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScooterByTimePauseDTO other = (ScooterByTimePauseDTO) obj;
		return idScooter == other.idScooter && time == other.time;
	}
}
