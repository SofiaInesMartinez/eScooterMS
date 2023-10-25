package tpe.administrationMS.DTO;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;

public class DTOScooterRequest {
	@NotNull(message = "id shouldn't be null")
	private long id;
	private Date lastMaintenanceDate;
	private float kilometers;
	
	
	public DTOScooterRequest() {
		super();
	}

	public DTOScooterRequest(long id, Date lastMaintenanceDate, float kilometers) {
		super();
		this.id = id;
		this.lastMaintenanceDate = lastMaintenanceDate;
		this.kilometers = kilometers;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public float getKms() {
		return kilometers;
	}

	public void setKms(float kilometers) {
		this.kilometers = kilometers;
	}
	public Date getLastMaintenanceDate() {
		return lastMaintenanceDate;
	}
	public void setLastMaintenanceDate(Date lastMaintenanceDate) {
		this.lastMaintenanceDate = lastMaintenanceDate;
	}


	@Override
	public String toString() {
		return "DTOScooterRequest [id=" + id + ", lastMaintenanceDate=" + lastMaintenanceDate + ", kilometers="
				+ kilometers + "]";
	}
}
