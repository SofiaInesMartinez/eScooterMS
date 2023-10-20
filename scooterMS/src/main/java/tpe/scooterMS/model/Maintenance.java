package tpe.scooterMS.model;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class Maintenance {
	
	@Id
	private long id;
	@Column(nullable=false)
	private String description;
	@Column(nullable = false)
	private Date startDate;
	@Column(nullable = true)
	private Date finishDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idScooter")
	@JoinColumn(name="id_scooter")
	private Scooter scooter;
	
	public Maintenance(long id, String description, Date startDate, Date finishDate, Scooter scooter) {
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

	public void setScooter(Scooter scooter) {
		this.scooter = scooter;
	}
	
	@Override
	public String toString() {
		return "Maintenance [id=" + id + ", description=" + description + ", startDate=" + startDate + ", finishDate="
				+ finishDate + ", scooter=" + scooter + "]";
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
				&& id == other.id && Objects.equals(startDate, other.startDate)
				&& Objects.equals(scooter, other.scooter);
	}
	
	
	
	
}
