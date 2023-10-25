package tpe.tripms.dto;

import java.sql.Date;
import java.util.Objects;

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
	private long idScooter;
	
	public DTOMaintenanceRequest(long id, String description, Date startDate, Date finishDate, long idScooter) {
		super();
		this.id = id;
		this.description = description;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.idScooter = idScooter;
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

	public long getIdScooter() {
		return idScooter;
	}

	public void setIdScooter(long idScooter) {
		this.idScooter = idScooter;
	}

	@Override
	public String toString() {
		return "DTOMaintenanceRequest [id=" + id + ", description=" + description + ", startDate=" + startDate
				+ ", finishDate=" + finishDate + ", idScooter=" + idScooter + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOMaintenanceRequest other = (DTOMaintenanceRequest) obj;
		return Objects.equals(description, other.description) && Objects.equals(finishDate, other.finishDate)
				&& id == other.id && idScooter == other.idScooter && Objects.equals(startDate, other.startDate);
	}
}
