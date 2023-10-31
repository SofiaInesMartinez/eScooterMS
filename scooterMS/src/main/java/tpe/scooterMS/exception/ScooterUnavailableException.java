package tpe.scooterMS.exception;

@SuppressWarnings("serial")
public class ScooterUnavailableException extends Exception {
	public ScooterUnavailableException(long id) {
		super(String.format("The scooter with id %s is unavailable", id));
	}
}
