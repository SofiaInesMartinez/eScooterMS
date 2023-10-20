package tpe.scooterMS.DTO;

import java.sql.Date;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;

public class DTOScooterRequest {
	
	@NotNull(message = "id shouldn't be null")
	private long id;
	private Date lastMaintenanceDate;
	
	
	public DTOScooterRequest(long id, Date lastMaintenanceDate) {
		super();
		this.id = id;
		this.lastMaintenanceDate = lastMaintenanceDate;
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
		return "DTOScooterRequest [id=" + id + ", lastMaintenanceDate=" + lastMaintenanceDate + "]";
	}
}
