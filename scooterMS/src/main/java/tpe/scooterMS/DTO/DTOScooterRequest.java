package tpe.scooterMS.DTO;

import java.sql.Date;
import jakarta.validation.constraints.NotNull;

public class DTOScooterRequest {
	
	@NotNull(message = "id shouldn't be null")
	private long id;
	private Date lastMaintenanceDate;
	private float kilometers;
	@NotNull(message = "latitude shouldn't be null")
	private double latitude;
	@NotNull(message = "longitude shouldn't be null")
	private double longitude;
	
	
	public DTOScooterRequest(long id, Date lastMaintenanceDate, float kilometers, double latitude, double longitude) {
		super();
		this.id = id;
		this.lastMaintenanceDate = lastMaintenanceDate;
		this.kilometers = kilometers;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public float getKilometers() {
		return kilometers;
	}

	public void setKilometers(float kilometers) {
		this.kilometers = kilometers;
	}
	public Date getLastMaintenanceDate() {
		return lastMaintenanceDate;
	}
	public void setLastMaintenanceDate(Date lastMaintenanceDate) {
		this.lastMaintenanceDate = lastMaintenanceDate;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "DTOScooterRequest [id=" + id + ", lastMaintenanceDate=" + lastMaintenanceDate + ", kilometers="
				+ kilometers + "]";
	}

	
}
