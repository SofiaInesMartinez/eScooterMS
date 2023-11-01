package tpe.administrationMS.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DTOStopRequest {
	
	@NotNull(message = "id shouldn't be null")
	@Positive(message = "id shouldn't be negative or zero")
	private long id;
	@NotNull(message = "latitude shouldn't be null")
	private double latitude;
	@NotNull(message = "longitude shouldn't be null")
	private double longitude;
	
	public DTOStopRequest(long id, long coordinates, double latitude, double longitude) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
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
	public String toString() {
		return "DTOStopRequest [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
