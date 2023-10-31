package tpe.scooterMS.exception;

@SuppressWarnings("serial")
public class ScooterNotLocatedAtStopException extends Exception {
	public ScooterNotLocatedAtStopException(int id) {
		super(String.format("The scooter with id %s is not located at a stop", id));
	}
}
