package tpe.tripms.model;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TripPK implements Serializable {
	private int idTrip;
	private int idUser;
	private int idScooter;
}
