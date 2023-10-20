package tpe.scooterMS.DTO;

import java.sql.Date;
import java.util.Objects;

import tpe.scooterMS.model.Scooter;



public class DTOScooterResponse {
	private long id;
	private Date lastMaintenanceDate;
	
	
	public DTOScooterResponse(long id, Date lastMaintenanceDate) {
		super();
		this.id = id;
		this.lastMaintenanceDate = lastMaintenanceDate;
	}
	
	public DTOScooterResponse(Scooter scooter) {
		super();
		this.id = scooter.getId();
		this.lastMaintenanceDate = scooter.getLastMaintenanceDate();
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
		return "DTOScooterResponse [id=" + id + ", lastMaintenanceDate=" + lastMaintenanceDate + "]";
	}
	
	
}
