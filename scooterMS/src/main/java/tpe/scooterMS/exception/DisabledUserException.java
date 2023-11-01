package tpe.scooterMS.exception;

@SuppressWarnings("serial")
public class DisabledUserException extends Exception {
	public DisabledUserException(long id) {
		super(String.format("The user with id %s is disabled", id));
	}
}
