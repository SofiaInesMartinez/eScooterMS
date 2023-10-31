package tpe.scooterMS.exception;

@SuppressWarnings("serial")
public class TripInPauseException extends Exception {
	public TripInPauseException(int id) {
		super(String.format("Trip with id %s is already in pause", id));
	}
}
