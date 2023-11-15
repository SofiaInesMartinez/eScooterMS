package tpe.administrationMS.exception;

@SuppressWarnings("serial")
public class UserWithEmailAlreadyExistsException extends Exception {
	public UserWithEmailAlreadyExistsException(String email) {
		super(String.format("An user with the email %s already exists", email));
	}
}
