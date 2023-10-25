package tpe.scooterMS.DTO;

import tpe.scooterMS.model.Scooter;


public class ScooterByTimeDTO {
	private long idScooter;
	private long time;
	
	public ScooterByTimeDTO() {
		super();
	}
	
	public ScooterByTimeDTO(long id, long time) {
		super();
		this.idScooter = id;
		this.time = time;
	}
	
	public ScooterByTimeDTO(Scooter scooter) {
		super();
		this.idScooter = scooter.getId();
		this.time = scooter.getTime();
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
