package tpe.administrationMS.DTO;

public class DTOStopResponse {
	private long id;
	private long coordinates;
	
	public DTOStopResponse(long id, long coordinates) {
		super();
		this.id = id;
		this.coordinates = coordinates;
	}
	
	public DTOStopResponse() {
		super();
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
		return "DTOStopResponse [id=" + id + ", coordinates=" + coordinates + "]";
	}
	

}
