package tpe.scooterMS.exception;

@SuppressWarnings("serial")
public class TripReachedPauseTimeLimitException extends Exception {
	public TripReachedPauseTimeLimitException(int id) {
		super(String.format("Trip with id %s reached pause limit", id));
	}
}
