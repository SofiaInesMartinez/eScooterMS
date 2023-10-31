package tpe.scooterMS.exception;

@SuppressWarnings("serial")
public class TripNotPausedException extends Exception {
	public TripNotPausedException(int id) {
		super(String.format("Trip with id %s is not paused", id));
	}
}
