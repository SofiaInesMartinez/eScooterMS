package tpe.tripms.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DTOMaintenanceRequest {
	
	@NotNull(message = "id shouldn't be null")
	private long id;
	@NotNull(message = "description shouldn't be null")
	@NotEmpty(message = "description shouldn't be empty")
	private String description;
	@NotNull(message = "startDate shouldn't be null")
	@NotEmpty(message = "startDate shouldn't be empty")
	private Date startDate;
	private Date finishDate;
	@NotNull(message = "scooter shouldn't be null")
	private Scooter scooter;
	
	public DTOMaintenanceRequest(long id, String description, Date startDate, Date finishDate, Scooter scooter) {
		super();
		this.id = id;
		this.description = description;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.scooter = scooter;
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
