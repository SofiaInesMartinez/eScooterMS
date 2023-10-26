package tpe.administrationMS.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DTOStopRequest {
	@NotNull(message = "id shouldn't be null")
	private long id;
	@NotNull(message = "coordinates shouldn't be null")
	@NotEmpty(message = "coordinates shouldn't be empty")
	private long coordinates;
	
	public DTOStopRequest(long id, long coordinates) {
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

	public void setCoordinates(long coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		return "DTOStopRequest [id=" + id + ", coordinates=" + coordinates + "]";
	}
		

}
