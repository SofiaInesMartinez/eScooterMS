package tpe.scooterMS.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Stop implements Serializable {
	
	@Id
	private long id;
	@Column(nullable=false)
	private long coordinates;
	
	public Stop() {
		super();
	}
	
	public Stop(long id, long coordinates) {
		super();
		this.id = id;
		this.coordinates = coordinates;
	}
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getCoordinates() {
		return coordinates;
	}


	public void setCoordenadas(long coordinates) {
		this.coordinates = coordinates;
	}


	@Override
	public String toString() {
		return "Stop [id=" + id + ", coordinates=" + coordinates + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stop other = (Stop) obj;
		return coordinates == other.coordinates && id == other.id;
	}
	
	
}
