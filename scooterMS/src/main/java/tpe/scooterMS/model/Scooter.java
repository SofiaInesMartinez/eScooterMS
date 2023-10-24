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
	@Column(nullable= true)
	private float kilometers;
	@Column(nullable = false)
	private String status;
	@Column(nullable = true)
	private long totalTime;
	@Column(nullable = true)
	private long timePause;
	
	public Scooter(long id, Date lastMaintenanceDate, float kilometers) {
		super();
		this.id = id;
		this.lastMaintenanceDate = lastMaintenanceDate;
		this.kilometers = kilometers;
		this.status = "available";
		this.totalTime = 0;
		this.timePause = 0;
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
	
	
	
	
	
}
