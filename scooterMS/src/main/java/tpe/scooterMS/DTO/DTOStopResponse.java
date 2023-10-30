package tpe.scooterMS.DTO;

import java.util.Objects;

import tpe.scooterMS.model.Stop;

public class DTOStopResponse {
	
	private long id;
	private double latitude;
	private double longitude;
	
	public DTOStopResponse(long id, double latitude, double longitude) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public DTOStopResponse(Stop stop) {
		super();
		this.id = stop.getId();
		this.latitude = stop.getLatitude();
		this.longitude = stop.getLongitude();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(id, latitude, longitude);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOStopResponse other = (DTOStopResponse) obj;
		return id == other.id && Double.doubleToLongBits(latitude) == Double.doubleToLongBits(other.latitude)
				&& Double.doubleToLongBits(longitude) == Double.doubleToLongBits(other.longitude);
	}
}
