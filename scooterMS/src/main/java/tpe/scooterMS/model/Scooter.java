package tpe.scooterMS.model;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Scooter implements Serializable {
	
	@Id
	private long id;
	@Column(nullable = true)
	private Date lastMaintenanceDate;
	@Column(nullable= false)
	private float kilometers;
	@Column(nullable = false)
	private String status;
	@Column(nullable = false)
	private long totalTime; // minutos
	@Column(nullable = false)
	private long timePause; // minutos
	@Column(nullable = false)
	private double latitude;
	@Column(nullable = false)
	private double longitude;
	
	public Scooter() {
		super();
	}
	
	public Scooter(long id, Date lastMaintenanceDate, float kilometers, double latitude, double longitude) {
		super();
		this.id = id;
		this.lastMaintenanceDate = lastMaintenanceDate;
		this.kilometers = kilometers;
		this.status = "available";
		this.totalTime = 0;
		this.timePause = 0;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getTime() {
		return totalTime;
	}

	public void setTime(long time) {
		this.totalTime = time;
	}

	public long getTimePause() {
		return timePause;
	}

	public void setTimePause(long timePause) {
		this.timePause = timePause;
	}

	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
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
}
