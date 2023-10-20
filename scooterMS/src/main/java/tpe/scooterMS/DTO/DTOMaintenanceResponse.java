package tpe.scooterMS.DTO;

import java.sql.Date;

import tpe.scooterMS.model.Maintenance;
import tpe.scooterMS.model.Scooter;

public class DTOMaintenanceResponse {

	private long id;
	private String description;
	private Date startDate;
	private Date finishDate;
	private Scooter scooter;
	
	public DTOMaintenanceResponse(long id, String description, Date startDate, Date finishDate, Scooter scooter) {
		super();
		this.id = id;
		this.description = description;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.scooter = scooter;
	}

	public DTOMaintenanceResponse(Maintenance maintenance) {
		super();
		this.id = maintenance.getId();
		this.description = maintenance.getDescription();
		this.startDate = maintenance.getStartDate();
		this.finishDate = maintenance.getFinishDate();
		this.scooter = maintenance.getScooter();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Scooter getScooter() {
		return scooter;
	}

	public void setStudent(Scooter scooter) {
		this.scooter = scooter;
	}

	@Override
	public String toString() {
		return "DTOMaintenanceResponse [id=" + id + ", Description=" + description + ", startDate=" + startDate
				+ ", finishDate=" + finishDate + ", scooter=" + scooter + "]";
	}
	
	
	
	
}
