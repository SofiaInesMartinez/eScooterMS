package tpe.scooterMS.exception;

@SuppressWarnings("serial")
public class UserOnTripException extends Exception {
	public UserOnTripException(long id) {
		super(String.format("The specified user with id %s is on a trip", id));
	}
}
