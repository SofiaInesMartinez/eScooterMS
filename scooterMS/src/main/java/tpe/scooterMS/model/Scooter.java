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
	
	public Scooter(long id, Date lastMaintenanceDate) {
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
	
	
	
	
	
}
