package tpe.tripms.dto;

import java.util.Date;

import lombok.Data;
import tpe.tripms.model.Trip;

@Data
public class TripResponseDTO {
	private int idTrip;
	private int idScooter;
	private int idDestinationStop;
	private int idOriginStop;
	private Date startDate;
	private Date endDate;
	
	public TripResponseDTO(Trip trip) {
		this.idTrip = trip.getIdTrip();
		this.idScooter = trip.getIdScooter();
		this.idDestinationStop = trip.getIdDestinationStop();
		this.idOriginStop = trip.getIdOriginStop();
		this.startDate = trip.getStartDate();
		this.endDate = trip.getEndDate();
	}
}
//public class TripResponseDTO {
//	private int idTrip;
//	private UserResponseDTO user;
//	private ScooterResponseDTO scooter;
//	private StopResponseDTO destinationStop;
//	private StopResponseDTO originStop;
//	private Date startDate;
//	private Date endDate;
//	
//	public TripResponseDTO(Trip trip) {
//		this.idTrip = trip.getIdTrip();
//		this.user = new UserResponseDTO(trip.getUser());
//		this.scooter = new ScooterResponseDTO(trip.getScooter());
//		this.destinationStop = new StopResponseDTO(trip.getDestinationStop());
//		this.originStop = new StopResponseDTO(trip.getOriginStop());
//		this.startDate = trip.getStartDate();
//		this.endDate = trip.getEndDate();
//	}
//}
