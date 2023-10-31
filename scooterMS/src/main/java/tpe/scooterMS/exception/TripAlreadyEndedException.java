package tpe.scooterMS.exception;

@SuppressWarnings("serial")
public class TripAlreadyEndedException extends Exception {
	public TripAlreadyEndedException(int id) {
		super(String.format("Trip with id %s already ended", id));
	}
}
