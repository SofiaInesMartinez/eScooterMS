package tpe.maintenanceMS.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Maintenance implements Serializable {
	
	@Id
	private long id;
	@Column(nullable=false)
	private String description;
	@Column(nullable = false)
	private Date startDate;
	@Column(nullable = true)
	private Date finishDate;
	@Column(name = "id_scooter")
	private long idScooter;
	
	public Maintenance() {
		super();
	}
	
	public Maintenance(long id, String description, Date startDate, Date finishDate, long idScooter) {
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
		return "Maintenance [id=" + id + ", description=" + description + ", startDate=" + startDate + ", finishDate="
				+ finishDate + ", idScooter=" + idScooter + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, finishDate, id, idScooter, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Maintenance other = (Maintenance) obj;
		return Objects.equals(description, other.description) && Objects.equals(finishDate, other.finishDate)
				&& id == other.id && idScooter == other.idScooter && Objects.equals(startDate, other.startDate);
	}
}
