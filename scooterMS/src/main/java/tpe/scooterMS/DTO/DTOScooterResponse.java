package tpe.scooterMS.DTO;

import java.sql.Date;
import java.util.Objects;

import tpe.scooterMS.model.Scooter;



public class DTOScooterResponse {
	private long id;
	private Date lastMaintenanceDate;
	private float kilometers;
	
	public DTOScooterResponse(long id, Date lastMaintenanceDate, float kilometers) {
		super();
		this.id = id;
		this.lastMaintenanceDate = lastMaintenanceDate;
		this.kilometers = kilometers;
	}
	
	public DTOScooterResponse(Scooter scooter) {
		super();
		this.id = scooter.getId();
		this.lastMaintenanceDate = scooter.getLastMaintenanceDate();
	}
	
	public float getKms() {
		return kilometers;
	}

	public void setKms(float kilometers) {
		this.kilometers = kilometers;
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

	@Override
	public String toString() {
		return "DTOScooterResponse [id=" + id + ", lastMaintenanceDate=" + lastMaintenanceDate + ", kilometers="
				+ kilometers + "]";
	}

	
	
	
}
