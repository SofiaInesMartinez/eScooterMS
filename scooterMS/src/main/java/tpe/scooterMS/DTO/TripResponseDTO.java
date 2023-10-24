package tpe.scooterMS.DTO;

import java.util.Date;

import lombok.Data;
import tpe.scooterMS.model.Trip;

@Data
public class TripResponseDTO {
	private long idTrip;
	private long idUser;
	private long idScooter;
	private Date startDate;
	private Date endDate;
	
	public TripResponseDTO(Trip trip) {
		this.idTrip = trip.getIdTrip();
		this.idUser = trip.getUser().getId();
		this.idScooter = trip.getScooter().getId();
		this.startDate = trip.getStartDate();
		this.endDate = trip.getEndDate();
	}
}