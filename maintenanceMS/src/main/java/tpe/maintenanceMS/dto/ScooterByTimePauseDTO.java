package tpe.maintenanceMS.dto;

public class ScooterByTimePauseDTO {
	private long idScooter;
	private long time;
	
	public ScooterByTimePauseDTO() {
		super();
	}
	
	public ScooterByTimePauseDTO(long id, long time) {
		super();
		this.idScooter = id;
		this.time = time;
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
