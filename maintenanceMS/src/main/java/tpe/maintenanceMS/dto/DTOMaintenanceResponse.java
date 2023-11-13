package tpe.maintenanceMS.dto;

import java.util.Date;
import java.util.Objects;

import tpe.maintenanceMS.model.Maintenance;

public class DTOMaintenanceResponse {

	private long id;
	private String description;
	private Date startDate;
	private Date finishDate;
	private long idScooter;
	
	public DTOMaintenanceResponse(long id, String description, Date startDate, Date finishDate, long idScooter) {
		super();
		this.id = id;
		this.description = description;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.idScooter = idScooter;
	}

	public DTOMaintenanceResponse(Maintenance maintenance) {
		super();
		this.id = maintenance.getId();
		this.description = maintenance.getDescription();
		this.startDate = maintenance.getStartDate();
		this.finishDate = maintenance.getFinishDate();
		this.idScooter = maintenance.getIdScooter();
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
		return "DTOMaintenanceResponse [id=" + id + ", description=" + description + ", startDate=" + startDate
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
		DTOMaintenanceResponse other = (DTOMaintenanceResponse) obj;
		return Objects.equals(description, other.description) && Objects.equals(finishDate, other.finishDate)
				&& id == other.id && idScooter == other.idScooter && Objects.equals(startDate, other.startDate);
	}
}