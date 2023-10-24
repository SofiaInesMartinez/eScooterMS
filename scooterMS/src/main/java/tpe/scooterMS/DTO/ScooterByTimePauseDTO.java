package tpe.scooterMS.DTO;

import lombok.Data;
import tpe.scooterMS.model.Scooter;

@Data
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
	
	
}
