package tpe.scooterMS.DTO;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;

public class DTOCoordinatesRequest {
	@NotNull(message = "latitude shouldn't be latitude")
	private double latitude;
	@NotNull(message = "longitude shouldn't be latitude")
	private double longitude;
	
	public DTOCoordinatesRequest(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public DTOCoordinatesRequest() {
		super();
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
		return Objects.hash(latitude, longitude);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOCoordinatesRequest other = (DTOCoordinatesRequest) obj;
		return Double.doubleToLongBits(latitude) == Double.doubleToLongBits(other.latitude)
				&& Double.doubleToLongBits(longitude) == Double.doubleToLongBits(other.longitude);
	}
}
