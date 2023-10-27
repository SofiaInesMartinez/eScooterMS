package tpe.scooterMS.DTO;

import java.sql.Date;

import tpe.scooterMS.model.Scooter;



public class DTOScooterResponse {
	private long id;
	private Date lastMaintenanceDate;
	private float kilometers;
	private String status;
	
	public DTOScooterResponse(long id, Date lastMaintenanceDate, float kilometers, String status) {
		super();
		this.id = id;
		this.lastMaintenanceDate = lastMaintenanceDate;
		this.kilometers = kilometers;
		this.status = status;
	}
	
	public DTOScooterResponse(Scooter scooter) {
		super();
		this.id = scooter.getId();
		this.lastMaintenanceDate = scooter.getLastMaintenanceDate();
		this.kilometers = scooter.getKms();
		this.status = scooter.getStatus();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getLastMaintenanceDate() {
		return lastMaintenanceDate;
	}
	public void setLastMaintenanceDate(Date lastMaintenanceDate) {
		this.lastMaintenanceDate = lastMaintenanceDate;
	}
	public float getKilometers() {
		return kilometers;
	}
	public void setKilometers(float kilometers) {
		this.kilometers = kilometers;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DTOScooterResponse [id=" + id + ", lastMaintenanceDate=" + lastMaintenanceDate + ", kilometers="
				+ kilometers + "]";
	}

	
	
	
}
